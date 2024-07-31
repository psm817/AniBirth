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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final MemberService memberService;

    @GetMapping("/main")
    public String product() {
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
        model.addAttribute("member", member); // 모델에 isAdmin 속성 추가

        return "product/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProduct(id);

        model.addAttribute("product", product);

        return "product/detail";
    }

    @GetMapping("/create")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String create(Model model) {
        List<Product> productList = productService.getList();
        
        model.addAttribute("productList", productList);
        return "product/create";
    }

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String createContent(
            @RequestParam("title") String title, @RequestParam("description") String description,
            @RequestParam("price") int price,@RequestParam("thumbnail") MultipartFile thumbnail

    ) {
        productService.create(title, description, price, thumbnail);

        return "product/create";
    }
}
