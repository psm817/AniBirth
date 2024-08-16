package com.cod.AniBirth.article.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Article extends BaseEntity {

    private String title;
    @Column(length = 5000)
    private String content;
    private int viewCount;

}