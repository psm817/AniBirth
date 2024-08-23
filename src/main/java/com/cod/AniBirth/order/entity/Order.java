package com.cod.AniBirth.order.entity;


import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="product_order")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member buyer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String orderId;
    private String name;
    private boolean isPaid;
    private boolean isCanceled;
    private boolean isRefunded;

    private Long totalPrice;
    private LocalDateTime payDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<OrderItem> orderItemList = new ArrayList<>();


}