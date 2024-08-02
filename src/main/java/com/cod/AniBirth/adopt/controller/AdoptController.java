package com.cod.AniBirth.adopt.controller;

import com.cod.AniBirth.adopt.entity.AdoptReview;
import com.cod.AniBirth.adopt.form.AdoptForm;
import com.cod.AniBirth.adopt.form.AdoptionnoticeForm;
import com.cod.AniBirth.adopt.form.CreateReviewForm;
import com.cod.AniBirth.adopt.service.AdoptReviewService;
import com.cod.AniBirth.adopt.service.AdoptService;
import com.cod.AniBirth.animal.AnimalSearchDTO;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import com.cod.AniBirth.category.service.CategoryService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiListUI;
import java.awt.print.PrinterGraphics;
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
    public String list(Authentication authentication, Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw,
                       @ModelAttribute AnimalSearchDTO searchDTO
    ) {

        Member member = null;

        if(authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        Page<Animal> paging = animalService.getList(page, kw, searchDTO);

        model.addAttribute("member", member);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
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
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String show_create(AdoptionnoticeForm adoptionnoticeForm, Authentication authentication, Model model) {
        Member member = memberService.findByUsername(authentication.getName());
        model.addAttribute("member",member);
        return "adopt/adoption_noticeForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid AdoptionnoticeForm adoptionnoticeForm, @RequestParam("thumbnail")MultipartFile thumbnail, Principal principal,Authentication authentication) {
//        Member member = memberService.getMemberById(id);
        Member member = memberService.getMemberByUsername(authentication.getName());
//        Member member1 = memberService.findByUsername(authentication.getName());

        animalService.create(adoptionnoticeForm.getAge(),adoptionnoticeForm.getName(),adoptionnoticeForm.getClassification(),
                adoptionnoticeForm.getHairColor(),adoptionnoticeForm.getMemo(),adoptionnoticeForm.getGender(),adoptionnoticeForm.getRegId(),
                adoptionnoticeForm.getRescueDate(),adoptionnoticeForm.getWeight(),adoptionnoticeForm.getThumbnail(),adoptionnoticeForm.getAdoptionStatusCd(),adoptionnoticeForm.getSpecies(),member);

        return "redirect:/adopt/list";

    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Animal animal = animalService.getAnimal(id);

        model.addAttribute("animal", animal);
        return "adopt/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/apply/{id}")
    public String showApplyForm(AdoptForm adoptForm, @PathVariable("id") Long id, Model model) {
        Animal animal = animalService.getAnimal(id);

        model.addAttribute("animal", animal);

        return "adopt/form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/apply/{id}")
    public String submitApplyForm(@Valid AdoptForm adoptForm, @RequestParam("isGender") boolean isGender,
                                  @RequestParam("isMarried") boolean isMarried, @RequestParam("file")MultipartFile file,Principal principal, @PathVariable("id") Long id) {
        Member member = memberService.getMemberByUsername(principal.getName());
        Animal animal = animalService.getAnimal(id);

        adoptService.apply(adoptForm.getName(),adoptForm.getPhone(),adoptForm.getEmail(),adoptForm.getAge(),adoptForm.getCompany(),
                adoptForm.getPostCode(),adoptForm.getAddress(),adoptForm.getDetailAddress(),adoptForm.getExtraAddress(),
                isGender,isMarried,adoptForm.getFile(),member,animal);

        animalService.modify(member,animal);



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

        // 이전 다음 페이지
        AdoptReview prevVR = adoptReviewService.getPreviousVR(id);
        AdoptReview nextVR = adoptReviewService.getNextVR(id);

        model.addAttribute("adoptReview", adoptReview);
        model.addAttribute("prevVR", prevVR != null ? prevVR.getId() : null);
        model.addAttribute("nextVR", nextVR != null ? nextVR.getId() : null);
        return "adopt/review_detail";
    }

    @PostMapping("/image-upload")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public String uploadEditorImage(@RequestParam("image") final MultipartFile image) {
        return adoptReviewService.uploadImage(image);
    }

}