package com.cod.AniBirth.animal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Animal {
    @Id
    @Column(unique = true)
    private String animalSeq;

    private String adoptionStatusCd;
    private String age;
    private String classification;
    private String fileNm;
    private String filePath;
    private String foundPlace;
    private String gender;
    private String gu;
    private String hairColor;
    private int hitCnt;
    private String memo;
    private String modDtTm;
    private String noticeDate;
    private String regDtTm;
    private String regId;
    private String rescueDate;
    private String species;
    private String weight;
}
