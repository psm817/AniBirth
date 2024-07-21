package com.cod.global.initData;

import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class NotProd {
    @Bean
    CommandLineRunner initData(AnimalService animalService) {
        System.out.println("animal test");
        return args -> {
            Animal animal1 = animalService.create("복순이", "브라운", "개");
            Animal animal2 = animalService.create("복자", "검정", "고양이");
        };
    }
}