package com.cod.AniBirth.donation.service;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.repository.AccountRepository;
import com.cod.AniBirth.donation.entity.Donation;
import com.cod.AniBirth.donation.repository.DonationRepository;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private final DonationRepository donationRepository;

    public List<Member> getAllRecipients() {
        return memberRepository.findByAuthority(1); // 권한이 1인 멤버들(기업/보호소)만 가져옴
    }

    public boolean donatePoints(Member donor, Long recipientId, Long amount) {
        Account donorAccount = accountRepository.findByMemberId(donor.getId())
                .orElseThrow(() -> new IllegalArgumentException("Donor account not found"));

        if (donorAccount.getAniPoint() >= amount) {
            donorAccount.setAniPoint(donorAccount.getAniPoint() - amount);
            accountRepository.save(donorAccount);

            Member recipient = memberRepository.findById(recipientId)
                    .orElseThrow(() -> new IllegalArgumentException("Recipient not found"));

            Donation donation = new Donation();
            donation.setAmount(BigDecimal.valueOf(amount));
            donation.setDonor(donor);
            donation.setRecipient(recipient);
            donationRepository.save(donation);

            return true;
        }
        return false;
    }
}
