package com.cod.AniBirth.member.controller;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.service.AccountService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/member/myPage")
@RequiredArgsConstructor
public class MyPageMemberController {
    private final MemberService memberService;
    private final AccountService accountService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myProfile")
    public String myPage(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("account", account);

        return "member/myPage/myProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/volunteer")
    public String myVolunteer(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("account", account);

        return "member/myPage/volunteer";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/adopt")
    public String myAdopt(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("account", account);

        return "member/myPage/adopt";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/donation")
    public String myDonation(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("account", account);

        return "member/myPage/donation";
    }
}
