package com.cod.AniBirth.global.initData;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class Dev {
    @Bean
    public ApplicationRunner init(MemberService memberService) {
        return args -> {
            Member member1 = memberService.signup("admin", "admin", "admin", "admin@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 0, 1);
            Member member2 = memberService.signup("company1", "company1", "company1", "company1@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 1, 1);
            Member member3 = memberService.signup("company2", "company2", "company2", "company2@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 1, 0);
            Member member4 = memberService.signup("user1", "user1", "user1", "user1@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 2, 1);
            Member member5 = memberService.signup("user2", "user2", "user2", "user2@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 2, 1);
        };
    }
}
