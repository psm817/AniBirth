package com.cod.AniBirth.order.repository;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.order.entity.Order;
import com.cod.AniBirth.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT oi.product FROM OrderItem oi JOIN oi.order o WHERE o.buyer = :member AND oi.isPaid = true")
    List<Product> findPurchasedProductsByMember(@Param("member") Member member);

    @Query("SELECT COUNT(oi) > 0 FROM OrderItem oi JOIN oi.order o WHERE o.buyer = :buyer AND oi.product = :product AND o.isPaid = true AND o.isCanceled = false AND o.isRefunded = false")
    boolean existsByBuyerAndProduct(@Param("buyer") Member buyer, @Param("product") Product product);



}
