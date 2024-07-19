package com.cod.AniBirth;

import com.cod.AniBirth.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("테스트 공지사항 생성")
    void testCreateArticles() {
        for (int i = 1; i <= 200; i++) {
            String title = String.format("테스트 공지사항:[%03d]", i);
            String content = String.format("테스트 내용 %03d", i);
            articleService.create(title, content);
        }
    }
}
