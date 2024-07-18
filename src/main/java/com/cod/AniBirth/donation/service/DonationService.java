package com.cod.AniBirth.donation.service;

import com.cod.AniBirth.shelter.entity.Shelter;
import com.cod.AniBirth.shelter.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {


    private final ShelterRepository shelterRepository;

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }
}
