package com.cod.AniBirth.animal.service;

import com.cod.AniBirth.ApiResponse;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.repository.AnimalRepository;

import com.cod.global.jwt.JwtProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final JwtProvider jwtProvider;
    public List<Animal> getList() {
        return animalRepository.findAll();
    }

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public void saveAnimals(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse apiResponse = objectMapper.readValue(json, ApiResponse.class);
        List<Animal> animals = apiResponse.getMsgBody().getItems();

        animalRepository.saveAll(animals);
    }

}
