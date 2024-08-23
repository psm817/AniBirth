package com.cod.AniBirth.order.service;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.repository.AccountRepository;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.order.entity.Order;
import com.cod.AniBirth.order.entity.OrderItem;
import com.cod.AniBirth.order.repository.OrderItemRepository;
import com.cod.AniBirth.order.repository.OrderRepository;
import com.cod.AniBirth.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final AccountRepository accountRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public boolean payWithPoints(Member member, int amount) {
        Account account = accountRepository.findByMemberId(member.getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (account.getAniPoint() >= amount) {
            account.setAniPoint(account.getAniPoint() - amount);
            accountRepository.save(account);
            return true;
        } else {
            return false;
        }
    }
    @Transactional
    public void saveOrder(Order order, List<OrderItem> orderItems) {
        orderRepository.save(order);
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.save(orderItem); // Save each OrderItem
        }
    }

    public boolean existsByMemberAndProduct(Member member, Product product) {
        return orderRepository.existsByBuyerAndProduct(member, product);
    }

}
