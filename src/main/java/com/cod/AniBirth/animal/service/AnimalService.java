package com.cod.AniBirth.animal.service;

import com.cod.AniBirth.ApiResponse;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.repository.AnimalRepository;
import com.cod.AniBirth.global.security.DataNotFoundException;
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

        for (Animal animal : animals) {
            if ("1".equals(animal.getAdoptionStatusCd())) {
                animal.setAdoptionStatusCd("입양 공고중");
            } else if ("2".equals(animal.getAdoptionStatusCd())) {
                animal.setAdoptionStatusCd("입양가능");
            } else if ("3".equals(animal.getAdoptionStatusCd())) {
                animal.setAdoptionStatusCd("입양예정");
            } else if ("4".equals(animal.getAdoptionStatusCd())) {
                animal.setAdoptionStatusCd("입양완료");
            } else if ("7".equals(animal.getAdoptionStatusCd())) {
                animal.setAdoptionStatusCd("주인반환");
            }
            else {
                animal.setAdoptionStatusCd("기타상태");
            }
            if ("1".equals(animal.getGender())) {
                animal.setGender("암");
            } else if ("2".equals(animal.getGender())) {
                animal.setGender("수");
            }
            if ("1".equals(animal.getClassification())) {
                animal.setClassification("개");
            } else if ("2".equals(animal.getClassification())) {
                animal.setClassification("고양이");
            } else {
                animal.setClassification("기타동물");
            }

            // gu 변환
            switch (animal.getGu()) {
                case "1":
                    animal.setGu("동구");
                    break;
                case "2":
                    animal.setGu("중구");
                    break;
                case "3":
                    animal.setGu("서구");
                    break;
                case "4":
                    animal.setGu("유성구");
                    break;
                case "5":
                    animal.setGu("대덕구");
                    break;
                default:
                    animal.setGu("기타구");
                    break;
            }
        }

        animalRepository.saveAll(animals);
    }

    public Animal getAnimal(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);

        if( animal.isPresent()) {
            return animal.get();
        } else {
            throw new DataNotFoundException("animal not found");
        }
    }

}
