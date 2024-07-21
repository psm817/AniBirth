package com.cod.AniBirth.point.controller;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.point.entity.Point;
import com.cod.AniBirth.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
        private final MemberService memberService;



    @GetMapping("/recharge")
    @PreAuthorize("isAuthenticated()")
    public String showRechargePage() {
        return "points/recharge";
    }

    @GetMapping("/success")
    @PreAuthorize("isAuthenticated()")
    public String rechargeSuccess(@RequestParam("paymentKey") String paymentKey,
                                  @RequestParam("amount") int amount,
                                  @RequestParam("orderId") String orderId,
                                  Model model) {
        // 현재 로그인된 회원 정보를 가져옵니다.
        Member member = memberService.getCurrentMember();
        pointService.rechargePoints(member, amount);
        model.addAttribute("message", "포인트 충전이 완료되었습니다.");
        return "points/success";
    }

    @GetMapping("/fail")
    @PreAuthorize("isAuthenticated()")
    public String rechargeFail(@RequestParam("code") String code,
                               @RequestParam("message") String message,
                               Model model) {
        model.addAttribute("message", message);
        return "points/fail";
    }
    @GetMapping("/balance")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public Map<String, Integer> getBalance() {
        Member member = memberService.getCurrentMember();
        Point point = pointService.getOrCreatePoint(member);
        Map<String, Integer> response = new HashMap<>();
        response.put("balance", point.getBalance());
        return response;
    }


}