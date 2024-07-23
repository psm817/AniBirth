package com.cod.AniBirth.donation.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.member.entity.Member;

import jakarta.persistence.*;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Setter
public class Donation extends BaseEntity {


    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Member donor;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Member recipient;

}
