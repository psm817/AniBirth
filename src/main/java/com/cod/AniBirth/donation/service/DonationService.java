package com.cod.AniBirth.donation.service;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.repository.AccountRepository;
import com.cod.AniBirth.donation.entity.Donation;
import com.cod.AniBirth.donation.repository.DonationRepository;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DonationService {

    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private final DonationRepository donationRepository;

    public List<Member> getAllRecipients() {
        return memberRepository.findByAuthority(1); // 권한이 1인 멤버들(기업/보호소)만 가져옴
    }

    @Transactional
    public boolean donatePoints(Member donor, Long recipientId, Long amount) {
        try {
            Account donorAccount = accountRepository.findByMemberId(donor.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Donor account not found"));

            if (donorAccount.getAniPoint() >= amount) {
                donorAccount.setAniPoint(donorAccount.getAniPoint() - amount);
                accountRepository.save(donorAccount);

                Member recipient = memberRepository.findById(recipientId)
                        .orElseThrow(() -> new IllegalArgumentException("Recipient not found"));

                Account recipientAccount = accountRepository.findByMemberId(recipient.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Recipient account not found"));

                recipientAccount.setAniPoint(recipientAccount.getAniPoint() + amount);
                accountRepository.save(recipientAccount);

                Donation donation = new Donation();
                donation.setAmount(amount);
                donation.setDonor(donor);
                donation.setRecipient(recipient);
                donationRepository.save(donation);

                return true;
            } else {
                log.warn("Insufficient points for donor: {}", donor.getUsername());
                return false;
            }
        } catch (Exception e) {
            log.error("Error during donation process", e);
            return false;
        }
    }

    public List<Object[]> getTopDonors() {
        List<Object[]> topDonors = donationRepository.findTopDonors();
        log.info("Top Donors: {}", topDonors);
        return topDonors.stream()
                .limit(3) // 상위 3명으로 제한
                .collect(Collectors.toList());
    }
}
