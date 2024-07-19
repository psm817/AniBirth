package com.cod.AniBirth.article.service;

import com.cod.AniBirth.article.entity.Article;
import com.cod.AniBirth.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return articleRepository.findAll();
    }

    public void saveArticle(Article article) {
        articleRepository.save(article);
    }



    public Page<Article> getList(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }


    public void create(String title, String content) {
        Article article = Article.builder()
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .viewCount(0)
                .build();

        articleRepository.save(article);
    }

}
