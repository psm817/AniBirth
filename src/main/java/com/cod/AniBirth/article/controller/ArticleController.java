package com.cod.AniBirth.article.controller;

import com.cod.AniBirth.article.entity.Article;
import com.cod.AniBirth.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
//
//    @GetMapping("/list")
//    public String list(Model model) {
//        List<Article> articleList = articleService.getList();
//        model.addAttribute("articleList", articleList);
//        return "article/list";
//    }

@GetMapping("/list")
public String list(Model model,
                   @RequestParam(value = "page", defaultValue = "0") int page,
                   @RequestParam(value = "size", defaultValue = "20") int size) {
    Pageable pageable = PageRequest.of(page, size); // 페이지당 항목 수를 size로 설정
    Page<Article> paging = articleService.getList(pageable); // Pageable을 이용하여 페이지네이션을 수행
    model.addAttribute("paging", paging);
    return "article/list";
}

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Article article) {
        article.setCreateDate(LocalDateTime.now());
        article.setViewCount(0);

        articleService.saveArticle(article);
        return "redirect:/article/list";
    }
}
