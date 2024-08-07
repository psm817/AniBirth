package com.cod.AniBirth.cart.controller;

import com.cod.AniBirth.cart.entity.CartItem;
import com.cod.AniBirth.cart.service.CartService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.product.entity.Product;
import com.cod.AniBirth.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final MemberService memberService;
    private final ProductService productService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String list(Principal principal, Model model) {
        Member member = memberService.findByUsername(principal.getName());
        List<CartItem> cartList = cartService.getList(member);
        int totalPrice = cartService.getTotalPrice(member);

        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);

        return "cart/list";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, Principal principal) {
        Product product = productService.getProduct(id);
        Member member = memberService.findByUsername(principal.getName());

        cartService.add(product, member);

        return "redirect:/cart/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        cartService.delete(id);
        return "redirect:/cart/list";
    }

    @PostMapping("/update-quantity/{id}")
    public String updateQuantity(
            @PathVariable("id") Long id,
            @RequestParam("quantity") Long quantity,
            Authentication authentication
    ) {
        Member member = memberService.findByUsername(authentication.getName());
        if (member == null) {
            return "redirect:/login";
        }

        cartService.updateQuantity(id, quantity);
        return "redirect:/cart/list";
    }
}
