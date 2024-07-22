package com.cod.AniBirth.account.entity;

import com.cod.AniBirth.base.entity.BaseEntity;
import com.cod.AniBirth.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
public class Account extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "member_id", unique = true)
    private Member member;              // 사람 한명당 계좌 하나

    private String account_number;      // 계좌번호
    private Long aniPoint;              // 포인트
}
