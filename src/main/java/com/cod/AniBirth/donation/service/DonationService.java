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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DonationService {

    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private final DonationRepository donationRepository;

    public List<Member> getAllRecipients() {
        return memberRepository.findByAuthorityInAndIsActive(List.of(0, 1), 1);
    }

    @Transactional
    public boolean donatePoints(Member donor, Member recipient, Long amount) {
        try {
            Account donorAccount = accountRepository.findByMemberId(donor.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Donor account not found"));

            if (donorAccount.getAniPoint() >= amount) {
                donorAccount.setAniPoint(donorAccount.getAniPoint() - amount);
                accountRepository.save(donorAccount);

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

    // 상위 기부자를 가져옵니다
    public List<Object[]> getTopDonors() {
        List<Object[]> topDonors = donationRepository.findTopDonors();
        log.info("Top Donors: {}", topDonors);

        // 상위 3명까지만 추출합니다.
        List<Object[]> reorderedTopDonors = new ArrayList<>();
        if (topDonors.size() > 1) {
            reorderedTopDonors.add(topDonors.get(1)); // 2등
        }
        if (topDonors.size() > 0) {
            reorderedTopDonors.add(topDonors.get(0)); // 1등
        }
        if (topDonors.size() > 2) {
            reorderedTopDonors.add(topDonors.get(2)); // 3등
        }

        return reorderedTopDonors;
    }

    public List<Donation> getDonationsByDonor(Member donor) {
        return donationRepository.findByDonor(donor);
    }

    public Long getDonationCountByDonor(Member donor) {
        return donationRepository.countByDonorId(donor.getId());
    }

    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    public List<Donation> getDonationsReceivedByMember(Member member) {
        return donationRepository.findByRecipient(member);
    }

    public Long getDonationCountReceivedByMember(Member member) {
        return donationRepository.countByRecipient(member);
    }

}
