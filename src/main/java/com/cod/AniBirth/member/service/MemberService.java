package com.cod.AniBirth.member.service;



import com.cod.AniBirth.email.service.EmailService;
import com.cod.AniBirth.global.security.DataNotFoundException;

import com.cod.AniBirth.member.entity.Member;

import com.cod.AniBirth.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


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


    public Member findByUsername(String name) {
        Optional<Member> member = memberRepository.findByUsername(name);

        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("Member not found");
        }
    }

    public Member findByUsernameAndEmail(String id, String email) {
        return memberRepository.findByUsernameAndEmail(id, email);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
