package com.cod.AniBirth.home.controller;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;

    @GetMapping("/")
    public String root(Principal principal, Model model) {
        if(principal != null) {
            Member member = memberService.findByUsername(principal.getName());

            model.addAttribute("member", member);
        }

        return "home/main";
    }
}
