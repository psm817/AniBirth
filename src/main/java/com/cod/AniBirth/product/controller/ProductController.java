package com.cod.AniBirth.product.controller;


import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.product.entity.Product;
import com.cod.AniBirth.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final MemberService memberService;

    @GetMapping("/main")
    public String product(Model model, Authentication authentication) {
        List<Product> products = productService.getTopRatedProducts(8);

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("products", products);
        model.addAttribute("member", member);

        return "product/main";
    }

    @GetMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value="kw", defaultValue="") String kw,
            Authentication authentication
    ) {
        Page<Product> paging = productService.getList(page, kw);

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("member", member);

        return "product/list";
    }

    @GetMapping("/list/high_price")
    public String listByHighPrice(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value="kw", defaultValue="") String kw,
            Authentication authentication
    ) {
        Page<Product> paging = productService.getListByHighPrice(page, kw);

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("member", member);

        return "product/list/high_price";
    }

    @GetMapping("/list/low_price")
    public String listByLowPrice(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value="kw", defaultValue="") String kw,
            Authentication authentication
    ) {
        Page<Product> paging = productService.getListByLowPrice(page, kw);

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("member", member);

        return "product/list/low_price";
    }

    @GetMapping("/list/high_rating")
    public String listByHighStar(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value="kw", defaultValue="") String kw,
            Authentication authentication
    ) {
        Page<Product> paging = productService.getListByHighRating(page, kw);

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("member", member);

        return "product/list/high_rating";
    }

    @GetMapping("/list/high_hit")
    public String listByHighHit(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value="kw", defaultValue="") String kw,
            Authentication authentication
    ) {
        Page<Product> paging = productService.getListByHighHit(page, kw);

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("member", member);

        return "product/list/high_hit";
    }

    @GetMapping("/list/food")
    public String food(Model model, @RequestParam(value = "page", defaultValue = "0") int page, Authentication authentication) {
        Page<Product> paging = productService.getFoodCategory(page);

        Member member = null;

        if(authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("paging", paging);
        model.addAttribute("member", member);

        return "product/food";
    }

    @GetMapping("/list/accessory")
    public String accessory(Model model, @RequestParam(value = "page", defaultValue = "0") int page, Authentication authentication) {
        Page<Product> paging = productService.getAccessoryCategory(page);

        Member member = null;

        if(authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("paging", paging);
        model.addAttribute("member", member);

        return "product/accessory";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, Authentication authentication) {
        Product product = productService.getProduct(id);
        double averageStarRating = productService.calculateAverageStarRating(product);

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        productService.plusHit(product);

        model.addAttribute("product", product);
        model.addAttribute("member", member);
        model.addAttribute("averageStarRating", averageStarRating);

        return "product/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(Model model, Authentication authentication) {
        Member member = memberService.findByUsername(authentication.getName());
        model.addAttribute("member", member);
        return "product/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createContent(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam("category") String category,
            @RequestParam("thumbnail") MultipartFile thumbnail,
            @RequestParam(value = "shippingFee", defaultValue = "3000") int shippingFee, // 배송비 기본값 설정
            Authentication authentication
    ) {
        Member member = memberService.findByUsername(authentication.getName());
        productService.create(title, description, price, category, thumbnail, member, shippingFee);

        return "redirect:/product/list?productCreateSuccess=true";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyContent(
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam("category") String category,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam(value = "shippingFee", defaultValue = "3000") int shippingFee // 배송비 기본값 설정
    ) {
        productService.modify(id, title, description, price, category, thumbnail, shippingFee);
        return "redirect:/product/detail/%d?productModifySuccess=true".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/product/list?productDeleteSuccess=true";
    }
}
