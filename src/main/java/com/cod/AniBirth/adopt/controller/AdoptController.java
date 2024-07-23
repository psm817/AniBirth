package com.cod.AniBirth.adopt.controller;

import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adopt")
public class AdoptController {
    private final AnimalService animalService;
    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {

        Page<Animal> paging = animalService.getList(page, kw);
        List<Animal> animals = animalService.findAll();
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("animals", animals);

        return "adopt/list";
    }

}
