package com.cod.AniBirth.review.service;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.product.entity.Product;
import com.cod.AniBirth.review.entity.Review;
import com.cod.AniBirth.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void create(Product product, Member member, String content, int starRating) {
        Review review = Review.builder()
                .member(member)
                .product(product)
                .content(content)
                .starRating(starRating)
                .build();

        reviewRepository.save(review);
    }

    public Review getReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);

        if (review.isPresent()) {
            return review.get();
        } else {
            throw new RuntimeException("review not found");
        }
    }

    public void modify(Review review, String content, int starRating) {
        review.setContent(content);
        review.setModifyDate(LocalDateTime.now());
        review.setStarRating(starRating);

        reviewRepository.save(review);
    }

    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    public boolean existByMemberAndProduct(Member member, Product product) {
        return reviewRepository.existsByBuyerAndProduct(member, product);
    }
}
