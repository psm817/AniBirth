package com.cod.AniBirth;

import com.cod.AniBirth.article.service.ArticleService;
import com.cod.AniBirth.article.service.QaService;
import com.cod.AniBirth.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private QaService qaService;

    @Test
    @DisplayName("테스트 공지사항 생성")
    void testCreateArticles() {
//        String username = "user";

        for (int i = 1; i <= 20; i++) {
            String title = String.format("테스트 공지사항:[%03d]", i);
            String content = String.format("테스트 내용 %03d", i);
            articleService.create(title, content);
        }
    }
    @Test
    @DisplayName("테스트 Q&A 생성")
    void testCreateQas() {
        String username = "user1";

        for (int i = 1; i <= 20; i++) {
            String title = String.format("테스트 Q&A:[%03d]", i);
            String content = String.format("테스트 내용 %03d", i);
            qaService.create(title, content, username);
        }
    }
}
