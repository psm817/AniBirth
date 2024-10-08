package com.cod.AniBirth.adopt.entity;

import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptApply extends BaseEntity {

    private String name;
    private String phone;

    @NotBlank(message = "이메일 형식에 맞게 입력해주세요")
    @Email
    private String email;

    private String age;
    private String company; //직업,직장명

    private String postCode; //우편번호
    private String address; //주소
    private String detailAddress; //상세주소
    private String extraAddress; //참고사항

    private String gender; //성별
    private String marriedStatus; //결혼여부
    private String file;

    @OneToOne
    private Animal animal;

    @ManyToOne
    private Member adoptee;
}