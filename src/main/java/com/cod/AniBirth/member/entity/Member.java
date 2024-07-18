package com.cod.AniBirth.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String username;
    private String password;
    private String nickname;    // 활동명 or 기업/보호소명
    private String email;
    private String phone;
    private String address;
    private String thumbnailImg;

    private int authority;   // 보호소/기업(1) or 회원(2)
    private int isActive;   // 승인(1), 대기(0)
}
