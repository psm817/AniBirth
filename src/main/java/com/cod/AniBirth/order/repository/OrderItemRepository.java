package com.cod.AniBirth.order.repository;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.order.entity.Order;
import com.cod.AniBirth.order.entity.OrderItem;
import com.cod.AniBirth.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
