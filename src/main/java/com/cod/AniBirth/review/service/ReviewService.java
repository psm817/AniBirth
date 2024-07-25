package com.cod.AniBirth.review.service;


import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.product.entity.Product;
import com.cod.AniBirth.review.entity.Review;
import com.cod.AniBirth.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository questionRepository;

    public void create(Product product, Member member, String content) {
        Review q = Review.builder()
                .member(member)
                .product(product)
                .content(content)
                .build();

        questionRepository.save(q);
    }

    public Review getQuestion(Long id) {
        Optional<Review> question = questionRepository.findById(id);

        if (question.isPresent()) {
            return question.get();
        } else {
            throw new RuntimeException("question not found");
        }
    }

    public void modify(Review question, String content) {
        Review modifyQuestion = question.toBuilder()
                .content(content)
                .build();

        questionRepository.save(modifyQuestion);
    }

    public void delete(Review question) {
        questionRepository.delete(question);
    }
}
