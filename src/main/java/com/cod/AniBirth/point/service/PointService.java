package com.cod.AniBirth.point.service;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.repository.AccountRepository;
import com.cod.AniBirth.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final AccountRepository accountRepository;

    public Account getOrCreateAccount(Member member) {
        return accountRepository.findByMemberId(member.getId())
                .orElseGet(() -> {
                    Account newAccount = Account.builder()
                            .member(member)
                            .aniPoint(0L)
                            .build();
                    return accountRepository.save(newAccount);
                });
    }

    public void rechargePoints(Member member, int amount, String transactionId) {
        Account account = getOrCreateAccount(member);
        if (account.isTransactionProcessed(transactionId)) {
            return;
        }
        account.setAniPoint(account.getAniPoint() + amount);
        account.addProcessedTransaction(transactionId);
        accountRepository.save(account);
    }

    public Long getBalance(Member member) {
        Account account = getOrCreateAccount(member);
        return account.getAniPoint();
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public boolean deductPoints(Member member, int amount) {
        Account account = getOrCreateAccount(member);
        if (account.getAniPoint() >= amount) {
            account.setAniPoint(account.getAniPoint() - amount);
            accountRepository.save(account);
            return true;
        } else {
            return false;
        }
    }
}
