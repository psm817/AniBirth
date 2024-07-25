package com.cod.AniBirth.donation.controller;

import com.cod.AniBirth.donation.service.DonationService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final MemberService memberService;

    @GetMapping("/donate")
    public String showDonatePage(Model model, Authentication authentication) {
        List<Member> recipients = donationService.getAllRecipients();
        model.addAttribute("recipients", recipients);
        model.addAttribute("isAuthenticated", authentication != null && authentication.isAuthenticated());
        return "donation/donation";
    }

    @PostMapping("/submit")
    public String donate(
            @RequestParam("recipientId") Long recipientId,
            @RequestParam("amount") Long amount,
            Model model) {

        // 현재 로그인된 회원 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member donor = memberService.findByUsername(authentication.getName());

        boolean success = donationService.donatePoints(donor, recipientId, amount);

        if (success) {
            model.addAttribute("message", "후원이 성공적으로 완료되었습니다.");
            return "donation/success";
        } else {
            model.addAttribute("message", "포인트가 부족합니다.");
            return "donation/fail";
        }
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex, Model model) {
        model.addAttribute("message", "모든 필드를 입력하세요.");
        return "donation/fail";
    }

    @GetMapping("/donation/fail")
    public String donationFail(Model model) {
        model.addAttribute("message", "후원에 실패했습니다. 다시 시도해주세요.");
        return "fail"; // The name of your Thymeleaf template (fail.html)
    }
}
