package com.cod.AniBirth.article.controller;

import com.cod.AniBirth.article.entity.Qa;
import com.cod.AniBirth.article.service.QaService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.global.security.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qa")
public class QaController {

    private final QaService qaService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model, Authentication authentication) {
        List<Qa> qaList = qaService.getAllQas();
        Member member = null;

        if(authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        model.addAttribute("qaList", qaList);
        model.addAttribute("member", member);
        return "qa/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("qa", new Qa());
        return "qa/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Qa qa) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        } else {
            throw new IllegalStateException("Unknown principal type");
        }

        Member member = memberService.findByUsername(username);
        qa.setMember(member);
        qa.setCreateDate(LocalDateTime.now());
        qaService.saveQa(qa);
        return "redirect:/qa/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Qa qa = qaService.getQaById(id);
        if (qa == null) {
            throw new DataNotFoundException("QA not found");
        }
        model.addAttribute("qa", qa);
        return "qa/detail";
    }

    @PostMapping("/comment/{id}")
    public String addComment(@PathVariable Long id, @RequestParam String adminComment, Authentication authentication) {
        Qa qa = qaService.getQaById(id);
        if (qa == null) {
            throw new DataNotFoundException("QA not found");
        }

        // Check if the user is an admin
        Member member = memberService.findByUsername(authentication.getName());
        if (member.getAuthority() != 0) {
            throw new SecurityException("Only admins can add comments");
        }

        qa.setAdminComment(adminComment);
        qaService.saveQa(qa);
        return "redirect:/qa/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        qaService.deleteQa(id);
        return "redirect:/qa/list";
    }
}
