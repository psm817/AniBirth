package com.cod.AniBirth.donation.controller;

import com.cod.AniBirth.donation.service.DonationService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String donate(
            @RequestParam("recipientId") Long recipientId,
            @RequestParam(value = "amount", required = false) Long amount,
            RedirectAttributes redirectAttributes) {

        if (amount == null || amount <= 0) {
            redirectAttributes.addFlashAttribute("message", "금액을 입력하세요.");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/donation/donate";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member donor = memberService.findByUsername(authentication.getName());
        Member recipient = memberService.getMemberById(recipientId);

        if (donor == null || recipient == null) {
            redirectAttributes.addFlashAttribute("message", "후원자가 유효하지 않습니다.");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/donation/donate";
        }

        boolean success = donationService.donatePoints(donor, recipient, amount);

        if (success) {
            redirectAttributes.addFlashAttribute("message", "후원이 성공적으로 완료되었습니다.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "포인트가 부족합니다.");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }

        return "redirect:/donation/donate";
    }



}
