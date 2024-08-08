package com.cod.AniBirth.order.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.product.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItem extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Order order;
    @ManyToOne(fetch = LAZY)
    private Product product;

    private LocalDateTime payDate;
    private int price; // 판매가
    private int payPrice; // 결제금액
    private boolean isPaid; // 결제여부
}
