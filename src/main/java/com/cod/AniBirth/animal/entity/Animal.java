package com.cod.AniBirth.animal.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
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
public class Animal extends BaseEntity {
    private String animalSeq;

    //Todo 0일때 입양대기, 1이면 입양완료 기본값을 0으로 변경 데이터를 불러
    private String adoptionStatusCd; //입양상태
    //Todo 입양날짜 생성, 입양보내는 멤버, 입양을 하는 멤버

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
