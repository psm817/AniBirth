package com.cod.AniBirth.member.service;


import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member signup(String username, String password, String nickname, String email,
                         String phone, String address, String thumbnailImg, int authority, int isActive) {
        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .phone(phone)
                .address(address)
                .thumbnailImg(thumbnailImg)
                .authority(authority)
                .isActive(isActive)
                .createDate(LocalDateTime.now())
                .build();

        if(authority == 2) {
            member.setIsActive(1);
        }

        return memberRepository.save(member);
    }

    public boolean usernameExists(String username) {
        return memberRepository.existsByUsername(username);
    }
}
