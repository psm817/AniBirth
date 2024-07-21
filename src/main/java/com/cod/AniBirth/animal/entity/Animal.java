package com.cod.AniBirth.animal.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
public class Animal extends BaseEntity {

    @Column(unique = true)
    private String name;
    public int age;
    public String adoptionStatusCd;
    public String classification; //유기동물구분(1.개 2.고양이 3.기타)
    public String foundPlace; //발견장소
    public String gender; //성별
    public String gu; //장소구분 동구,서구,중구
    public String species; //개
    public String weight; //몸무게
    public String hairColor; //털색

    public Map<String, Object> toClaims() {
        return Map.of(
                "name", getName()
        );
    }
}
