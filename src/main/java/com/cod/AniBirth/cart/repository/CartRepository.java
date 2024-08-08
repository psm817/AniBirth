package com.cod.AniBirth.cart.repository;


import com.cod.AniBirth.cart.entity.CartItem;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByMember(Member member);
    CartItem findByMemberAndProduct(Member member, Product product);
}
