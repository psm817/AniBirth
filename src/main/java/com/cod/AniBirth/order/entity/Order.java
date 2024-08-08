    package com.cod.AniBirth.order.entity;


    import com.cod.AniBirth.base.entity.BaseEntity;
    import com.cod.AniBirth.member.entity.Member;
    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.SuperBuilder;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;

    import static jakarta.persistence.FetchType.LAZY;

    @Entity
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Table(name="product_order")
    public class Order extends BaseEntity {
        @ManyToOne(fetch = LAZY)
        private Member buyer;

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