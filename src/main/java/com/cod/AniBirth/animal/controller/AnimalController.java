package com.cod.AniBirth.animal.controller;

import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    @Data
    public static class ListRequest {
        @NotBlank
        private String name;

        @NotBlank
        private String haircolor;
    }

    @PostMapping("/list")
    public Animal list(@Valid @RequestBody ListRequest listRequest, HttpServletResponse resp) {

        // 테스트용
//        resp.addHeader("Authentication", "JWT Token");

//        String accessToken = animalService.genAccessToken(listRequest.getName());
//        resp.addHeader("Authentication", accessToken);

        return animalService.findByName(listRequest.getName()).orElse(null);
    }
}
