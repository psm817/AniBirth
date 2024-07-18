package com.cod.AniBirth.donation.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.shelter.entity.Shelter;
import jakarta.persistence.*;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Setter
public class Donation extends BaseEntity {


    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelter;
}
