package com.cod.AniBirth.global.initData;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.service.AccountService;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import com.cod.AniBirth.category.entity.Category;
import com.cod.AniBirth.category.service.CategoryService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.product.service.ProductService;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.service.VolunteerService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class Dev {
    @Bean
    public ApplicationRunner init(MemberService memberService, AccountService accountService,
                                  ProductService productService, VolunteerService volunteerService) {

        return args -> {
            Member member1 = memberService.signup("admin", "admin", "admin", "admin@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 0, 1);
            Member member2 = memberService.signup("company1", "company1", "company1", "company1@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 1, 1);
            Member member3 = memberService.signup("company2", "company2", "company2", "company2@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 1, 0);
            Member member4 = memberService.signup("user1", "user1", "user1", "user1@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 2, 1);
            Member member5 = memberService.signup("user2", "user2", "user2", "user2@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 2, 1);
            Member member6 = memberService.signup("company3", "company3", "대전동물보호센터", "company3@test.com","010-1111-2222", "대전광역시 유성구 금남구즉로 1234 (금고동) 대전광역시 동물보호센터","/images/profile_default.jpg", 1, 1);
            Member member7 = memberService.signup("company4", "company4", "세종유기동물보호센터", "company4@test.com","010-1111-2222", "세종특별자치시 전동면 미륵당1길 188 (전동면)","/images/profile_default.jpg", 1, 1);
            Member member8 = memberService.signup("company5", "company5", "청양보호소", "company5@test.com","010-1111-2222", "충청남도 청양군 대치면 청산로 446-17 (대치면)","/images/profile_default.jpg", 1, 1);
            Member member9 = memberService.signup("company6", "company6", "원주시동물보호센터", "company6@test.com","010-1111-2222", "강원특별자치도 원주시 호저면 칠봉로 109-17","/images/profile_default.jpg", 1, 1);

            Account account1 = accountService.createOrUpdate(member1, "123-1234-1234", 0L);
            Account account2 = accountService.createOrUpdate(member2, "123-1234-1234", 0L);
            Account account3 = accountService.createOrUpdate(member3, "123-1234-1234", 0L);
            Account account4 = accountService.createOrUpdate(member4, "123-1234-1234", 0L);
            Account account5 = accountService.createOrUpdate(member5, "123-1234-1234", 0L);
            Account account6 = accountService.createOrUpdate(member6, "123-1234-1234", 0L);
            Account account7 = accountService.createOrUpdate(member7, "123-1234-1234", 0L);
            Account account8 = accountService.createOrUpdate(member8, "123-1234-1234", 0L);
            Account account9 = accountService.createOrUpdate(member9, "123-1234-1234", 0L);


            Volunteer volunteer1 = volunteerService.create("봉사활동 제목1", "봉사활동 내용1", "대전광역시 유성구 금남구즉로 1234 (금고동) 대전광역시 동물보호센터", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member6);
            Volunteer volunteer2 = volunteerService.create("봉사활동 제목2", "봉사활동 내용2", "세종특별자치시 전동면 미륵당1길 188 (전동면)", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member7);
            Volunteer volunteer3 = volunteerService.create("봉사활동 제목3", "봉사활동 내용3", "충청남도 청양군 대치면 청산로 446-17 (대치면)", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member8);
            Volunteer volunteer4 = volunteerService.create("봉사활동 제목4", "봉사활동 내용4", "강원특별자치도 원주시 호저면 칠봉로 109-17", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member9);
            Volunteer volunteer5 = volunteerService.create("봉사활동 제목5", "봉사활동 내용5", "대전광역시 서구 청사로 281", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member2);
            Volunteer volunteer6 = volunteerService.create("봉사활동 제목6", "봉사활동 내용6", "대전광역시 서구 청사로 281", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member3);
            Volunteer volunteer7 = volunteerService.create("봉사활동 제목6", "봉사활동 내용6", "대전광역시 서구 청사로 281", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member3);
            Volunteer volunteer8 = volunteerService.create("봉사활동 제목6", "봉사활동 내용6", "대전광역시 서구 청사로 281", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member3);
            Volunteer volunteer9 = volunteerService.create("봉사활동 제목6", "봉사활동 내용6", "대전광역시 서구 청사로 281", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member3);
            Volunteer volunteer10 = volunteerService.create("봉사활동 제목6", "봉사활동 내용6", "대전광역시 서구 청사로 281", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member3);
            Volunteer volunteer11 = volunteerService.create("봉사활동 제목6", "봉사활동 내용6", "대전광역시 서구 청사로 281", "2024-07-11", "2024-07-11", "2024-07-01", "/images/volunteer/volunteer_default.jpg", 20, member3);

        };
    }
}
