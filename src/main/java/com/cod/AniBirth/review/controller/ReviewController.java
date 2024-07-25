package com.cod.AniBirth.review.controller;


import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.product.entity.Product;
import com.cod.AniBirth.product.service.ProductService;
import com.cod.AniBirth.review.entity.Review;
import com.cod.AniBirth.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService questionService;
    private final ProductService productService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String create(
            @PathVariable("id") Long id,
            Principal principal,
            @RequestParam("content") String content
    ) {
        Product product = productService.getProduct(id);
        Member member = memberService.findByUsername(principal.getName());

        questionService.create(product, member, content);

        return String.format("redirect:/product/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id, Model model, Principal principal) {
        Review question = questionService.getQuestion(id);

        if ( !question.getMember().getUsername().equals(principal.getName()) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한 없음");
        }

        model.addAttribute("question", question);
        return "review/modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modify(
            @PathVariable("id") Long id,
            Principal principal,
            @RequestParam("content") String content
    ) {
        Review question = questionService.getQuestion(id);
        questionService.modify(question, content);
        long productId = question.getProduct().getId();

        if ( !question.getMember().getUsername().equals(principal.getName()) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한 없음");
        }

        return String.format("redirect:/product/detail/%s", productId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        Review question = questionService.getQuestion(id);
        questionService.delete(question);
        long productId = question.getProduct().getId();

        if ( !question.getMember().getUsername().equals(principal.getName()) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한 없음");
        }

        return String.format("redirect:/product/detail/%s", productId);
    }
}
