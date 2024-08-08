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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductService productService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String create(
            @PathVariable("id") Long id,
            Principal principal,
            @RequestParam("content") String content,
            @RequestParam("starRating") int starRating
    ) {
        Product product = productService.getProduct(id);
        Member member = memberService.findByUsername(principal.getName());

        reviewService.create(product, member, content, starRating);

        return String.format("redirect:/product/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modify(
            @PathVariable("id") Long id,
            Principal principal,
            @RequestParam("content") String content,
            @RequestParam("starRating") int starRating
    ) {
        Review review = reviewService.getReview(id);
        if (!review.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한 없음");
        }
        reviewService.modify(review, content, starRating); // starRating 추가

        Long num = review.getProduct().getId();

        return "redirect:/product/detail/%s".formatted(num);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        Review review = reviewService.getReview(id);

        reviewService.delete(review);

        return "redirect:/product/detail/%s?productReviewDeleteSuccess=true".formatted(id);
    }
}
