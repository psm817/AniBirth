package com.cod.AniBirth.adopt.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.member.entity.Member;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptReview extends BaseEntity {
    private String title;
    @Column(nullable = false, length = 3000)
    private String content;
    private String images;
    private int hit;

    @ManyToOne
    @JoinColumn(name = "wrtier_id")
    private Member writer;

}
