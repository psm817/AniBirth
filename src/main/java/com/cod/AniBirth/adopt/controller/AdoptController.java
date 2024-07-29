package com.cod.AniBirth.adopt.controller;

import com.cod.AniBirth.animal.AnimalSearchDTO;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import com.cod.AniBirth.category.entity.Category;
import com.cod.AniBirth.category.service.CategoryService;
import lombok.Getter;
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
    private final CategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw,
                       AnimalSearchDTO searchDTO
//                       @RequestParam(value = "classification", required = false) Long classificationId,
//                       @RequestParam(value = "gender", required = false) Long genderId,
//                       @RequestParam(value = "weight", required = false) String weight,
//                       @RequestParam(value = "age", required = false) String age
    ) {

//        Page<Animal> paging = animalService.getList(page, kw);
        Page<Animal> paging = animalService.getList(page, searchDTO);



//        List<Category> classifications = categoryService.getClassificationCategories();
//        List<Category> ages = categoryService.getAgeCategories();
//        List<Category> weights = categoryService.getWeightCategories();
//        List<Category> genders = categoryService.getGenderCategories();


        model.addAttribute("paging", paging);
        model.addAttribute("kw", searchDTO.getKeyword());
        model.addAttribute("searchDTO", searchDTO);
        model.addAttribute("classifications", categoryService.getClassifications());
        model.addAttribute("genders", categoryService.getGenders());
        model.addAttribute("weights", categoryService.getWeights());
        model.addAttribute("ages", categoryService.getAges());

        return "adopt/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Animal animal = animalService.getAnimal(id);

        model.addAttribute("animal", animal);
        return "adopt/detail";
    }

}
