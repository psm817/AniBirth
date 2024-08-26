package com.cod.AniBirth.review.repository;


import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.product.entity.Product;
import com.cod.AniBirth.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT COUNT(r) > 0 FROM Review r WHERE r.member = :member AND r.product = :product")
    boolean existsByBuyerAndProduct(@Param("member") Member member, @Param("product") Product product);
}