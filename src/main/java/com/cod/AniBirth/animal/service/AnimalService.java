package com.cod.AniBirth.animal.service;

import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.repository.AnimalRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
//    private final JwtProvider jwtProvider;
    public List<Animal> getList() {
        return animalRepository.findAll();
    }

    public Optional<Animal> findByName(String name) {
        return animalRepository.findByName(name);
    }

    public Animal create(String name, String color, String classification) {
        Animal animal = Animal.builder()
                .name(name)
                .hairColor(color)
                .age(11)
                .classification(classification)
                .build();

        animalRepository.save(animal);

        return animal;
    }

//    public String genAccessToken (String name) {
//        Animal animal = findByName(name).orElse(null);
//
//        if ( animal == null ) return null;
//
//        return jwtProvider.genToken(animal.toClaims(), 60 * 60 * 24 * 365);
//
//    }
}
