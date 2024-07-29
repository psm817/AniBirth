package com.cod.AniBirth.volunteer.controller;

import com.cod.AniBirth.calendar.service.CalendarService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/volunteer")
public class VolunteerController {
    private final MemberService memberService;
    private final VolunteerService volunteerService;
    private final CalendarService calendarService;

    @GetMapping("/list")
    public String volunteer(Authentication authentication, Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Volunteer> paging = volunteerService.getAllVolunteer(page);

        Member member = null;

        if(authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("member", member);
        model.addAttribute("paging", paging);

        return "volunteer/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create() {
        return "volunteer/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@RequestParam("title") String title, @RequestParam("startDate") String startDate,
                         @RequestParam("endDate") String endDate, @RequestParam("deadLineDate") String deadLineDate,
                         @RequestParam("location") String location, @RequestParam("limit") int limit,
                         @RequestParam("thumbnailImg") MultipartFile thumbnailImg, @RequestParam("content") String content,
                         Principal principal) {
        String imageFileName = storeProfilePicture_v(thumbnailImg);

        Member member = memberService.getMemberByUsername(principal.getName());

        Volunteer volunteer = volunteerService.create(title, content, location, startDate, endDate, deadLineDate, imageFileName, limit, member, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        calendarService.create(title, start, end);

        return "redirect:/volunteer/list";
    }

    public String storeProfilePicture_v(MultipartFile profilePicture) {
        // 이미지 저장 디렉토리 경로
        String uploadDir = "C:\\work\\AniBirth\\src\\main\\resources\\static\\images\\volunteer";

        // 디렉토리가 존재하지 않으면 생성
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new IllegalStateException("Could not create upload directory", e);
            }
        }
        // 파일명 중복을 피하기 위해 임의의 파일명을 생성합니다.
        String fileName = UUID.randomUUID().toString(); // UUID로 파일명 생성
        String imageFileName = fileName + ".png"; // 파일 확장자 지정
        // 파일을 저장합니다.
        try {
            Path filePath = uploadPath.resolve(imageFileName);
            Files.copy(profilePicture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Could not store image file", e);
        }
        // 저장된 파일의 상대 경로를 반환합니다.
        return "/images/volunteer/" + imageFileName;
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, Authentication authentication) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);

        // String 날짜 변환하기
        if (volunteer.getStartDate() != null) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");

            LocalDateTime startDateTime = LocalDateTime.parse(volunteer.getStartDate(), inputFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(volunteer.getEndDate(), inputFormatter);


            String formattedStartDateTime = startDateTime.format(outputFormatter);
            model.addAttribute("formattedStartDateTime", formattedStartDateTime);

            String formattedEndDateTime = endDateTime.format(outputFormatter);
            model.addAttribute("formattedEndDateTime", formattedEndDateTime);
        }

        Member member = null;

        if(authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("volunteer", volunteer);
        model.addAttribute("member", member);

        return "volunteer/detail";
    }
}
