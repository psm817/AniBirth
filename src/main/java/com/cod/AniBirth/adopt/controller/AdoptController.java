package com.cod.AniBirth.adopt.controller;

import com.cod.AniBirth.adopt.entity.Adopt;
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
import com.cod.AniBirth.volunteer.entity.Volunteer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

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
                             @RequestParam(value = "kw", defaultValue = "") String kw,
                             Authentication authentication) {
        Member member = null;
        boolean canCreateReview = false;

        if (authentication != null) {
            member = memberService.findByUsername(authentication.getName());

            if (member != null) {
                // 해당 사용자가 입양한 동물 목록을 가져옵니다.
                List<Animal> adoptees = animalService.findByAdoptee(member);

                // 입양한 동물이 하나라도 있으면 리뷰 작성 권한을 부여합니다.
                if (!adoptees.isEmpty()) {
                    canCreateReview = true;
                }
            }
        }

        Page<AdoptReview> paging = adoptReviewService.getList(page);
//        Page<AdoptReview> paging = adoptReviewService.getList(page, kw);




        model.addAttribute("canCreateReview", canCreateReview);

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/review/modify/{id}")
    public String createReviewModify(CreateReviewForm createReviewForm, @PathVariable("id") Long id, Principal principal, Model model, Authentication authentication){


        AdoptReview adoptReview = this.adoptReviewService.getAdoptReviews(id);

        if(!adoptReview.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        model.addAttribute("adoptReview", adoptReview);

        createReviewForm.setTitle(adoptReview.getTitle());
        createReviewForm.setContent(adoptReview.getContent());
//        createReviewForm.setImages(adoptReview.getImages());

        return "adopt/create_review_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/review/modify/{id}")
    public String createReviewModify(@Valid CreateReviewForm createReviewForm, BindingResult bindingResult,
                                     Principal principal, @PathVariable("id") Long id,
                                     RedirectAttributes redirectAttributes,
                                     @RequestParam("images") MultipartFile images
            , Model model) {

        // 검증 에러가 있는 경우, 수정 폼 페이지로 리다이렉트
        if (bindingResult.hasErrors()) {
            return "adopt/create_review_form";
        }

//        Member member = memberService.getMemberByUsername(authentication.getName());
        Member member = memberService.getMemberByUsername(principal.getName());

        AdoptReview adoptReview = this.adoptReviewService.getAdoptReviews(id);
        if(!adoptReview.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.adoptReviewService.modify(adoptReview, createReviewForm.getTitle(), createReviewForm.getContent(), createReviewForm.getImages(), member);
        return "redirect:/adopt/review";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/review/delete/{id}")
    public String adoptReviewDelete(Principal principal, @PathVariable("id") Long id){
        AdoptReview adoptReview= this.adoptReviewService.getAdoptReviews(id);
        if(!adoptReview.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.adoptReviewService.delete(adoptReview);
        return "redirect:/adopt/review";
    }

    @GetMapping("/review/detail/{id}")
    public String review_detail(@PathVariable("id") Long id, Model model) {
        AdoptReview adoptReview = adoptReviewService.getreview(id);

        adoptReviewService.hit(adoptReview);

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