package com.cod.AniBirth.point.controller;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.service.AccountService;
import com.cod.AniBirth.global.message.Message;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final MemberService memberService;
    private final AccountService accountService;

    @GetMapping("/recharge")
    @PreAuthorize("isAuthenticated()")
    public String showRechargePage(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("account", account);
        return "points/recharge";
    }

    @GetMapping("/success")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView rechargeSuccess(
            @RequestParam("paymentKey") String paymentKey,
            @RequestParam("amount") int amount,
            @RequestParam("orderId") String orderId) {
        Member member = memberService.getCurrentMember();
        String transactionId = paymentKey + "-" + orderId; // Generate a unique transaction ID
        pointService.rechargePoints(member, amount, transactionId);

        ModelAndView mav = new ModelAndView("Message");
        mav.addObject("data", new Message("포인트 충전이 완료되었습니다.", "/points/recharge"));
        return mav;
    }

    @GetMapping("/fail")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView rechargeFail(
            @RequestParam("code") String code,
            @RequestParam("message") String message) {
        ModelAndView mav = new ModelAndView("Message");
        mav.addObject("data", new Message("포인트 충전을 실패했습니다.", "/points/recharge"));
        return mav;
    }
}
