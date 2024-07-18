package com.cod.AniBirth.member.controller;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup"; // 회원가입 페이지로 이동
    }

    @PostMapping("/signup")
    public String signupMember(@ModelAttribute Member member) {
        return "redirect:/login"; // 회원가입 후 로그인 페이지로 리다이렉트
    }

}
