package com.cod.AniBirth.article.entity;


import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Qa extends BaseEntity {

    private String title;
    private String content;
    //    private String adminComment; // 관리자 댓글
    private int viewCount; // 조회수 추가

    @ElementCollection
    @CollectionTable(name = "qa_admin_comments", joinColumns = @JoinColumn(name = "qa_id"))
    @Column(name = "comment")
    private List<String> adminComments = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "qa_comment_authors", joinColumns = @JoinColumn(name = "qa_id"))
    @Column(name = "author_username")
    private List<String> commentAuthors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")

    private Member member; // 글 작성자
}
