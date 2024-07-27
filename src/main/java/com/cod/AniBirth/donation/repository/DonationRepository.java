package com.cod.AniBirth.donation.repository;

import com.cod.AniBirth.donation.entity.Donation;
import com.cod.AniBirth.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT d.donor, SUM(d.amount) AS totalAmount FROM Donation d GROUP BY d.donor ORDER BY totalAmount DESC")
    List<Object[]> findTopDonors();
}
