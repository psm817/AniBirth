package com.cod.AniBirth.animal.service;

import com.cod.AniBirth.ApiResponse;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.repository.AnimalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Value("${custom.genFileDirPath}")
    private String imageDirectory;

    public Page<Animal> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("noticeDate"));
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sorts));

        return animalRepository.findAllByKeyword(kw, pageable);
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
