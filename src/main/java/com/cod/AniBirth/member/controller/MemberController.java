package com.cod.AniBirth.member.controller;

import com.cod.AniBirth.member.form.MemberForm;
import com.cod.AniBirth.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        return "member/signup"; // 회원가입 페이지로 이동
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public String signupMember(@Valid MemberForm memberForm, @RequestParam("thumbnailImg") MultipartFile thumbnailImg) {
        String imageFileName = storeProfilePicture(memberForm.getThumbnailImg());

        memberService.signup(memberForm.getUsername(), memberForm.getPassword(), memberForm.getNickname(),
                memberForm.getEmail(), memberForm.getPhone(), memberForm.getAddress(),
                imageFileName, memberForm.getAuthority(), memberForm.getIsActive());

        return "redirect:/member/login"; // 회원가입 후 로그인 페이지로 리다이렉트
    }

    public String storeProfilePicture(MultipartFile profilePicture) {
        // 이미지 저장 디렉토리 경로


        String uploadDir = "C:\\work\\AniBirth\\src\\main\\resources\\static\\images\\profile";

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
        String imageFileName = fileName + ".jpg"; // 파일 확장자 지정
        // 파일을 저장합니다.
        try {
            Path filePath = uploadPath.resolve(imageFileName);
            Files.copy(profilePicture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Could not store image file", e);
        }
        // 저장된 파일의 상대 경로를 반환합니다.
        return "/images/profile/" + imageFileName;
    }
}
