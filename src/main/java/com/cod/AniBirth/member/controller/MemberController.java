package com.cod.AniBirth.member.controller;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        return "member/signup"; // 회원가입 페이지로 이동
    }

    @PostMapping("/signup")
    public String signupMember() {
        return "redirect:/member/login"; // 회원가입 후 로그인 페이지로 리다이렉트
    }

}
