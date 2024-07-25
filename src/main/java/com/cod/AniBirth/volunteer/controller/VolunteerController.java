package com.cod.AniBirth.volunteer.controller;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/volunteer")
public class VolunteerController {
    private final MemberService memberService;
    private final VolunteerService volunteerService;

    @GetMapping("/list")
    public String volunteer(Authentication authentication, Model model,
                            @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Volunteer> paging = volunteerService.getAllVolunteer(page);

        Member member = null;

        if(authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("member", member);
        model.addAttribute("paging", paging);

        return "volunteer/list";
    }
}
