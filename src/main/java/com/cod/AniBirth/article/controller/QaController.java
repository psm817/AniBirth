package com.cod.AniBirth.article.controller;

import com.cod.AniBirth.article.entity.Article;
import com.cod.AniBirth.article.entity.Qa;
import com.cod.AniBirth.article.service.QaService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.global.security.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qa")
public class QaController {

    private final QaService qaService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "10") int size,
                       Authentication authentication) {

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));
        Page<Qa> paging = qaService.getList(pageable);
        model.addAttribute("paging", paging);
        model.addAttribute("member", member);

        return "qa/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("qa", new Qa());
        return "qa/form";
    }

    @PostMapping("/create")
    public String create(@RequestParam("title") String title,
                         @RequestParam("content") String content,
                         Authentication authentication) {


        Member member = memberService.findByUsername(authentication.getName());

        qaService.create(title, content, member , 0);

        return "redirect:/qa/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model, Authentication authentication) {
        qaService.increaseViewCount(id);
        Qa qa = qaService.getQaById(id);
        if (qa == null) {
            throw new DataNotFoundException("해당 QA를 찾을 수 없습니다.");
        }

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        Qa prevQa = qaService.getPreviousQa(id);
        Qa nextQa = qaService.getNextQa(id);

        model.addAttribute("qa", qa);
        model.addAttribute("member", member);
        model.addAttribute("isEditingComment", false); // 초기 상태는 수정 모드가 아님
        model.addAttribute("editingComment", null); // 수정할 댓글 내용 초기화
        model.addAttribute("prevQaId", prevQa != null ? prevQa.getId() : null);
        model.addAttribute("nextQaId", nextQa != null ? nextQa.getId() : null);

        return "qa/detail";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, Authentication authentication) {
        Qa qa = qaService.getQaById(id);
        if (qa == null) {
            throw new DataNotFoundException("해당 QA를 찾을 수 없습니다.");
        }

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        if (!qa.getMember().getUsername().equals(username)) {
            throw new SecurityException("게시물 수정 권한이 없습니다.");
        }

        model.addAttribute("qa", qa);
        return "qa/form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content, Authentication authentication) {
        Qa existingQa = qaService.getQaById(id);

        qaService.edit(existingQa,title, content);



        return "redirect:/qa/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Authentication authentication) {
        Qa qa = qaService.getQaById(id);
        if (qa == null) {
            throw new DataNotFoundException("해당 QA를 찾을 수 없습니다.");
        }

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        if (!qa.getMember().getUsername().equals(username)) {
            throw new SecurityException("게시물 삭제 권한이 없습니다.");
        }

        qaService.deleteQa(id);
        return "redirect:/qa/list";
    }

    @PostMapping("/comment/{id}")
    @ResponseBody
    public String addAdminComment(@PathVariable("id") Long id,
                                  @RequestParam("adminComment") String adminComment,
                                  Authentication authentication) {

        Member member = null;
        if (authentication != null && authentication.isAuthenticated()) {
            member = memberService.findByUsername(authentication.getName());
        }

        // 댓글 추가
        Long commentId = qaService.addAdminComment(id, adminComment, member);

        // 댓글 ID를 응답으로 반환
        return commentId.toString(); // 댓글 ID를 응답으로 반환
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/remove/{id}")
    public String removeComment(@PathVariable("id") Long id,
                                @RequestParam("comment") String comment,
                                Authentication authentication) {

        Member member = memberService.findByUsername(authentication.getName());
        qaService.removeComment(id, comment, member);
        return "redirect:/qa/" + id;
    }


}
