package com.cod.AniBirth.member.controller;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.service.AccountService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.repository.MemberRepository;
import com.cod.AniBirth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/member/myPage")
@RequiredArgsConstructor
public class MyPageMemberController {
    private final MemberService memberService;
    private final AccountService accountService;
    private final MemberRepository memberRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myProfile")
    public String myPage(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);
        List<Member> memberList = memberService.getAllMember();

        model.addAttribute("member", member);
        model.addAttribute("account", account);
        model.addAttribute("memberList", memberList);

        return "member/myPage/myProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/approve")
    public String approve(@RequestParam(value = "username", defaultValue = "") String username ) {
        Member member = memberService.findByUsername(username);
        member.setIsActive(1);
        memberRepository.save(member);

        return "redirect:/member/myPage/myProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/volunteer")
    public String myVolunteer(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());

        model.addAttribute("member", member);

        return "member/myPage/volunteer";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/adopt")
    public String myAdopt(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());

        model.addAttribute("member", member);

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/market")
    public String myMarket(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("account", account);

        return "member/myPage/market";
    }
}
