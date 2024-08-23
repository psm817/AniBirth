package com.cod.AniBirth.animal.service;

import com.cod.AniBirth.ApiResponse;
import com.cod.AniBirth.animal.AnimalSearchDTO;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.repository.AnimalRepository;
import com.cod.AniBirth.category.repository.CategoryRepository;
import com.cod.AniBirth.global.security.DataNotFoundException;
import com.cod.AniBirth.member.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final CategoryRepository categoryRepository;


    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

//    public Page<Animal> getList(int page, String kw) {
    public Page<Animal> getList(int page, String kw, AnimalSearchDTO searchDTO) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("noticeDate"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        System.out.println("getClassification : " + searchDTO.getClassification());
        System.out.println("getGender : " + searchDTO.getGender());
        System.out.println("getWeight : " + searchDTO.getWeight());
        System.out.println("getAge : " + searchDTO.getAge());

        return animalRepository.findAllByKeyword(
                pageable,
                kw,
                searchDTO.getClassification(),
                searchDTO.getGender(),
                searchDTO.getWeight(),
                searchDTO.getAge()
        );
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
                animal.setCategorynum(5);//5번 개
            } else if ("2".equals(animal.getClassification())) {
                animal.setClassification("고양이");
                animal.setCategorynum(6);//6번 고양이
            } else {
                animal.setClassification("기타동물");
                animal.setCategorynum(7);//7번 기타동물
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

    public List<Animal> getRecentAdoptes() {
        return animalRepository.findTop4ByOrderByCreateDateDesc();
    }

    public void create(String age, String name, String classification, String hairColor, String memo, String gender,
                       String regId, String rescueDate, String weight, MultipartFile thumbnail, String adoptionStatusCd, String species, Member member) {
        String thumbnailRelPath = "images/adoptionnotice/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

        File parentDir = thumbnailFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            thumbnail.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Animal animal = Animal.builder()
                .name(name)
                .age(age)
                .adoptionStatusCd(adoptionStatusCd)
                .classification(classification)
                .hairColor(hairColor)
                .memo(memo)
                .gender(gender)
                .regId(regId)
                .rescueDate(rescueDate)
                .weight(weight)
                .species(species)
                .filePath(thumbnailRelPath)
                .adopter(member)
                .build();

        System.out.println("animal.filePath: " + animal.getFilePath());

        animalRepository.save(animal);
    }

    public void create(String age, String name, String classification, String hairColor, String memo, String gender,
                       String regId, String rescueDate, String weight, String thumbnail, String adoptionStatusCd, String species,Member member) {

        Animal animal = Animal.builder()
                .name(name)
                .age(age)
                .adoptionStatusCd(adoptionStatusCd)
                .classification(classification)
                .hairColor(hairColor)
                .memo(memo)
                .gender(gender)
                .regId(regId)
                .rescueDate(rescueDate)
                .weight(weight)
                .filePath(thumbnail)
                .species(species)
                .adopter(member)
                .build();

        animalRepository.save(animal);
    }

    public void modify(Member member, Animal animal) {
        animal.setAdoptee(member);

        animalRepository.save(animal);

    }
}