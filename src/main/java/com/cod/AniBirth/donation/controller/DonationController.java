package com.cod.AniBirth.donation.controller;

import com.cod.AniBirth.donation.service.DonationService;
import com.cod.AniBirth.global.message.Message;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
@Slf4j
public class DonationController {

    private final DonationService donationService;
    private final MemberService memberService;

    @GetMapping("/donate")
    public String showDonatePage(Model model, Authentication authentication) {
        List<Member> recipients = donationService.getAllRecipients();
        Member member = null;

        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        // 모델에 상위 기부자를 추가합니다
        List<Object[]> topDonors = donationService.getTopDonors();
        model.addAttribute("topDonors", topDonors != null ? topDonors : new ArrayList<>());
        model.addAttribute("recipients", recipients != null ? recipients : new ArrayList<>());
        model.addAttribute("isAuthenticated", authentication != null && authentication.isAuthenticated());
        model.addAttribute("member", member);
        return "donation/donation";
    }

    @PostMapping("/submit")
    public ModelAndView donate(
            @RequestParam("recipientId") Long recipientId,
            @RequestParam(value = "amount", required = false) Long amount) {

        ModelAndView mav = new ModelAndView();

        if (amount == null || amount <= 0) {
            mav.addObject("data", new Message("금액을 입력하세요.", "/donation/donate"));
            mav.setViewName("Message");
            return mav;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member donor = memberService.findByUsername(authentication.getName());
        Member recipient = memberService.getMemberById(recipientId);

        if (donor == null || recipient == null) {
            mav.addObject("data", new Message("후원자가 유효하지 않습니다.", "/donation/donate"));
            mav.setViewName("Message");
            return mav;
        }

        boolean success = donationService.donatePoints(donor, recipient, amount);

        if (success) {
            mav.addObject("data", new Message("후원이 성공적으로 완료되었습니다.", "/donation/donate"));
        } else {
            mav.addObject("data", new Message("포인트가 부족합니다.", "/donation/donate"));
        }

        mav.setViewName("Message");
        return mav;
    }

}