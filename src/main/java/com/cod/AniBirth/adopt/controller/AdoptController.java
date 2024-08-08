package com.cod.AniBirth.adopt.controller;

import com.cod.AniBirth.adopt.entity.AdoptReview;
import com.cod.AniBirth.adopt.form.AdoptForm;
import com.cod.AniBirth.adopt.form.CreateReviewForm;
import com.cod.AniBirth.adopt.service.AdoptReviewService;
import com.cod.AniBirth.adopt.service.AdoptService;
import com.cod.AniBirth.animal.AnimalSearchDTO;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import com.cod.AniBirth.category.service.CategoryService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiListUI;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adopt")
public class AdoptController {
    private final AnimalService animalService;
    private final CategoryService categoryService;
    private final AdoptService adoptService;
    private final AdoptReviewService adoptReviewService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw,
                       @ModelAttribute AnimalSearchDTO searchDTO
    ) {

//        Page<Animal> paging = animalService.getList(page, kw);
        Page<Animal> paging = animalService.getList(page, kw, searchDTO);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
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

    @GetMapping("/apply")
    public String showApplyForm(AdoptForm adoptForm) {
        return "adopt/form";
    }

    @PostMapping("/apply")
    public String submitApplyForm(@Valid AdoptForm adoptForm, @RequestParam("isGender") boolean isGender, @RequestParam("isMarried") boolean isMarried, @RequestParam("file")MultipartFile file) {
        adoptService.apply(adoptForm.getName(),adoptForm.getPhone(),adoptForm.getEmail(),adoptForm.getAge(),adoptForm.getCompany(),
                adoptForm.getPostCode(),adoptForm.getAddress(),adoptForm.getDetailAddress(),adoptForm.getExtraAddress(),
                isGender,isMarried,adoptForm.getFile());

        return "redirect:/adopt/list";
    }

    @GetMapping("/review")
    public String reviewlist(Model model,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<AdoptReview> paging = adoptReviewService.getList(page);
//        Page<AdoptReview> paging = adoptReviewService.getList(page, kw);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "adopt/review";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create_review")
    public String showcreateReviewForm(CreateReviewForm createReviewForm) {
        return "adopt/create_review_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create_review")
    public String submitCreateReview(@Valid CreateReviewForm createReviewForm, @RequestParam("images")MultipartFile images, Principal principal) {
        Member member = memberService.getMemberByUsername(principal.getName());
        adoptReviewService.create(createReviewForm.getTitle(),createReviewForm.getContent(),createReviewForm.getImages(), member);

        return "redirect:/adopt/review";

    }
    @GetMapping("/review/detail/{id}")
    public String review_detail(@PathVariable("id") Long id, Model model) {
        AdoptReview adoptReview = adoptReviewService.getreview(id);

        model.addAttribute("adoptReview", adoptReview);
        return "adopt/review_detail";
    }

}