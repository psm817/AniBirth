package com.cod.AniBirth.shelter.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.donation.entity.Donation;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;
@Entity
@Setter
public class Shelter extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "shelter")
    private List<Donation> donations;


}

