package com.cod.AniBirth.volunteer.controller;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/volunteer")
public class VolunteerController {
    private final MemberService memberService;

    @GetMapping("/")
    public String volunteer(Authentication authentication, Model model) {
        Member member = null;

        if(authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("member", member);

        return "volunteer/main";
    }
}
