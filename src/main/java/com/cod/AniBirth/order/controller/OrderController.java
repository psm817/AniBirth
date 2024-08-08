// OrderController.java

package com.cod.AniBirth.order.controller;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.service.AccountService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.order.service.OrderService;
import com.cod.AniBirth.point.service.PointService;
import com.cod.AniBirth.cart.service.CartService;
import com.cod.AniBirth.cart.entity.CartItem;
import com.cod.AniBirth.order.entity.Order;
import com.cod.AniBirth.order.entity.OrderItem;
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
    public String confirmPayment(
            Model model,
            @RequestParam(value = "orderId") String orderId,
            @RequestParam(value = "amount") Integer amount,
            @RequestParam(value = "paymentMethod") String paymentMethod,
            @RequestParam(value = "paymentKey", required = false) String paymentKey) throws Exception {

        Member member = memberService.getCurrentMember();
        int shippingFee = 3000; // Set shipping fee

        if ("points".equals(paymentMethod)) {
            boolean success = orderService.payWithPoints(member, amount);
            if (success) {
                createOrder(member, orderId, amount, shippingFee); // Create order after successful payment
                model.addAttribute("isSuccess", true);
                model.addAttribute("responseStr", "Payment completed using points");
                return "order/success";
            } else {
                model.addAttribute("isSuccess", false);
                model.addAttribute("responseStr", "Insufficient points");
                return "order/fail";
            }
        }

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
        model.addAttribute("isSuccess", isSuccess);

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();
        model.addAttribute("responseStr", jsonObject.toJSONString());

        if (isSuccess) {
            createOrder(member, orderId, amount, shippingFee); // Create order after successful payment
            model.addAttribute("method", (String) jsonObject.get("method"));
            model.addAttribute("orderName", (String) jsonObject.get("orderName"));

            if (jsonObject.get("method") != null) {
                switch ((String) jsonObject.get("method")) {
                    case "카드":
                        model.addAttribute("cardNumber", (String) ((JSONObject) jsonObject.get("card")).get("number"));
                        break;
                    case "가상계좌":
                        model.addAttribute("accountNumber", (String) ((JSONObject) jsonObject.get("virtualAccount")).get("accountNumber"));
                        break;
                    case "계좌이체":
                        model.addAttribute("bank", (String) ((JSONObject) jsonObject.get("transfer")).get("bank"));
                        break;
                    case "휴대폰":
                        model.addAttribute("customerMobilePhone", (String) ((JSONObject) jsonObject.get("mobilePhone")).get("customerMobilePhone"));
                        break;
                    default:
                        model.addAttribute("code", (String) jsonObject.get("code"));
                        model.addAttribute("message", (String) jsonObject.get("message"));
                        break;
                }
            } else {
                model.addAttribute("code", (String) jsonObject.get("code"));
                model.addAttribute("message", (String) jsonObject.get("message"));
            }
        }

        return isSuccess ? "order/success" : "order/fail";
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
                .orderItemList(new ArrayList<>()) // orderItemList 초기화
                .build();

        int totalOrderPoints = totalPrice + shippingFee; // Calculate total points including shipping fee

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(cartItem.getProduct())
                    .payDate(LocalDateTime.now())
                    .price(cartItem.getProduct().getPrice())
                    .payPrice((int) (cartItem.getProduct().getPrice() * cartItem.getQuantity()))
                    .isPaid(true)
                    .build();
            order.getOrderItemList().add(orderItem);

            // 상품 등록자에게 포인트 적립
            Member productOwner = cartItem.getProduct().getMember();
            int pointsToCredit = (int) (cartItem.getProduct().getPrice() * cartItem.getQuantity() + ((double) shippingFee / cartItems.size()));
            accountService.addPoints(productOwner, pointsToCredit);
        }

        order.setTotalPrice((long) (totalPrice + shippingFee)); // Set total price including shipping fee
        orderService.save(order);
        cartService.clearCart(member); // Clear cart after order creation
    }
}
