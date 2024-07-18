package com.cod.AniBirth.animal.service;

import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    public List<Animal> getList() {
        return animalRepository.findAll();
    }
}
