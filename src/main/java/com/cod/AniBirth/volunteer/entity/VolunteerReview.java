package com.cod.AniBirth.volunteer.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.member.entity.Member;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VolunteerReview extends BaseEntity {
    private String title;
    private String body;

    private int hit;            // 조회수

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;          // 작성자

    // TODO : 대표이미지 하나, 첨부파일할 최대 3개 이미지 추가해야됨
    private String thumbnailImg;            // 후기 대표 이미지

    @ElementCollection
    private List<String> subImages;  // 첨부 이미지 (최대 3개)
}
