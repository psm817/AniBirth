// OrderController.java

package com.cod.AniBirth.order.controller;

import com.cod.AniBirth.account.service.AccountService;
import com.cod.AniBirth.cart.entity.CartItem;
import com.cod.AniBirth.cart.service.CartService;
import com.cod.AniBirth.global.message.Message;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.order.entity.Order;
import com.cod.AniBirth.order.entity.OrderItem;
import com.cod.AniBirth.order.service.OrderService;
import com.cod.AniBirth.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Value("${custom.paymentSecretKey}")
    private String paymentSecretKey;

    private final OrderService orderService;
    private final PointService pointService;
    private final MemberService memberService;
    private final CartService cartService;
    private final AccountService accountService;

    @GetMapping("/checkout")
    public String checkout(Model model, Authentication authentication) {
        Member member = memberService.getCurrentMember();
        List<CartItem> cartList = cartService.getList(member);
        int totalPrice = cartService.getTotalPrice(member);
        int shippingFee = 3000; // Set shipping fee
        String orderId = UUID.randomUUID().toString(); // Generate a unique order ID

        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("totalWithShipping", totalPrice + shippingFee);
        model.addAttribute("orderId", orderId);
        model.addAttribute("address", member.getAddress()); // 기본 주소지 추가
        model.addAttribute("nickname", member.getNickname()); // Add nickname
        model.addAttribute("phone", member.getPhone());

        return "order/checkout";
    }

    @PostMapping("/confirm")
    public ModelAndView confirmPayment(
            @RequestParam(value = "orderId") String orderId,
            @RequestParam(value = "amount") Integer amount,
            @RequestParam(value = "paymentMethod") String paymentMethod,
            @RequestParam(value = "paymentKey", required = false) String paymentKey) throws Exception {

        ModelAndView mav = new ModelAndView();
        Member member = memberService.getCurrentMember();
        int shippingFee = 3000; // Set shipping fee

        if ("points".equals(paymentMethod)) {
            boolean success = orderService.payWithPoints(member, amount);
            if (success) {
                createOrder(member, orderId, amount, shippingFee); // 결제 성공 후 주문 생성
                mav.addObject("data", new Message("포인트로 결제가 완료되었습니다.", "/"));
                mav.setViewName("Message"); // 성공 메시지를 보여주는 페이지로 설정
            } else {
                mav.addObject("data", new Message("포인트가 부족합니다.", "/order/checkout"));
            }
            mav.setViewName("Message");
            return mav;
        }

        // TOSS payments API integration
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(paymentSecretKey.getBytes(StandardCharsets.UTF_8));
        String authorization = "Basic " + new String(encodedBytes, StandardCharsets.UTF_8);

        URL url = new URL("https://api.tosspayments.com/v1/payments/" + paymentKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorization);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes(StandardCharsets.UTF_8));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;
        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();

        if (isSuccess) {
            createOrder(member, orderId, amount, shippingFee); // Create order after successful payment
            mav.addObject("data", new Message("결제가 성공적으로 완료되었습니다.", "/order/checkout"));
        } else {
            mav.addObject("data", new Message("결제에 실패했습니다.", "/order/checkout"));
        }

        mav.setViewName("Message");
        return mav;
    }



    private void createOrder(Member member, String orderId, int totalPrice, int shippingFee) {
        List<CartItem> cartItems = cartService.getList(member);
        Order order = Order.builder()
                .buyer(member)
                .orderId(orderId)
                .name("Order " + orderId)
                .isPaid(true)
                .isCanceled(false)
                .isRefunded(false)
                .payDate(LocalDateTime.now())
                .orderItemList(new ArrayList<>()) // Initialize the orderItemList
                .build();

        List<OrderItem> orderItems = new ArrayList<>(); // List to hold OrderItems

        int totalOrderPoints = totalPrice + shippingFee; // Calculate total points including shipping fee

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(cartItem.getProduct())
                    .payDate(LocalDateTime.now())
                    .price(cartItem.getProduct().getPrice())
                    .quantity(cartItem.getQuantity()) // Set the quantity from CartItem
                    .payPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity()) // Calculate payPrice as price * quantity
                    .isPaid(true)
                    .build();

            orderItems.add(orderItem); // Add each OrderItem to the list
            order.getOrderItemList().add(orderItem); // Add to Order's list as well

            // Credit points to the product owner
            Member productOwner = cartItem.getProduct().getMember();
            int pointsToCredit = (int) (cartItem.getProduct().getPrice() * cartItem.getQuantity() + ((double) shippingFee / cartItems.size()));
            accountService.addPoints(productOwner, pointsToCredit);
        }

        order.setTotalPrice((long) (totalPrice + shippingFee)); // Set total price including shipping fee
        orderService.saveOrder(order, orderItems); // Save order and orderItems
        cartService.clearCart(member); // Clear cart after order creation
    }

}
