package com.cod.AniBirth.order.service;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.repository.AccountRepository;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.order.entity.Order;
import com.cod.AniBirth.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
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

}
