package com.cod.AniBirth.global.initData;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.service.AccountService;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import com.cod.AniBirth.article.service.ArticleService;
import com.cod.AniBirth.article.service.QaService;
import com.cod.AniBirth.calendar.service.CalendarService;
import com.cod.AniBirth.category.entity.Category;
import com.cod.AniBirth.category.service.CategoryService;
import com.cod.AniBirth.donation.entity.Donation;
import com.cod.AniBirth.donation.service.DonationService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.product.service.ProductService;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.service.VolunteerReviewService;
import com.cod.AniBirth.volunteer.service.VolunteerService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@Profile("dev")
public class Dev {
    @Bean
    public ApplicationRunner init(MemberService memberService, AccountService accountService,
                                  ProductService productService, VolunteerService volunteerService,
                                  CalendarService calendarService, VolunteerReviewService volunteerReviewService,
                                  DonationService donationService, ArticleService articleService,
                                  QaService qaService) {

        return args -> {
            // 회원 샘플
            Member member1 = memberService.signup("admin", "admin", "admin", "admin@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 0, 1);
            Member member2 = memberService.signup("company1", "company1", "company1", "company1@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 1, 1);
            Member member3 = memberService.signup("company2", "company2", "company2", "company2@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 1, 0);
            Member member4 = memberService.signup("user1", "user1", "user1", "user1@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 2, 1);
            Member member5 = memberService.signup("user2", "user2", "user2", "user2@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 2, 1);
            Member member6 = memberService.signup("company3", "company3", "대전동물보호센터", "company3@test.com","010-1111-2222", "대전광역시 유성구 금남구즉로 1234 (금고동) 대전광역시 동물보호센터","/images/profile_default.jpg", 1, 1);
            Member member7 = memberService.signup("company4", "company4", "세종유기동물보호센터", "company4@test.com","010-1111-2222", "세종특별자치시 전동면 미륵당1길 188 (전동면)","/images/profile_default.jpg", 1, 1);
            Member member8 = memberService.signup("company5", "company5", "청양보호소", "company5@test.com","010-1111-2222", "충청남도 청양군 대치면 청산로 446-17 (대치면)","/images/profile_default.jpg", 1, 1);
            Member member9 = memberService.signup("company6", "company6", "원주시동물보호센터", "company6@test.com","010-1111-2222", "강원특별자치도 원주시 호저면 칠봉로 109-17","/images/profile_default.jpg", 1, 1);
            Member member10 = memberService.signup("user3", "user3", "user3", "user3@test.com","010-1111-2222", "대전광역시 서구 청사로 281","/images/profile_default.jpg", 2, 1);

            // 포인트 샘플
            accountService.createOrUpdate(member1, "123-1234-1234", 0L);
            accountService.createOrUpdate(member2, "123-1234-1234", 0L);
            accountService.createOrUpdate(member3, "123-1234-1234", 0L);
            accountService.createOrUpdate(member4, "123-1234-1234", 0L);
            accountService.createOrUpdate(member5, "123-1234-1234", 0L);
            accountService.createOrUpdate(member6, "123-1234-1234", 0L);
            accountService.createOrUpdate(member7, "123-1234-1234", 0L);
            accountService.createOrUpdate(member8, "123-1234-1234", 0L);
            accountService.createOrUpdate(member9, "123-1234-1234", 0L);

            // 봉사활동 샘플
            Volunteer volunteer1 = volunteerService.create("유기견 목욕시키기", "봉사활동 내용1", "대전 유성구 금남구즉로 1234", "2024-07-14T09:50", "2024-07-14T17:50", "2024-07-10", "/images/volunteer/volunteer_default.jpg", 20, member6, 0);
            Volunteer volunteer2 = volunteerService.create("유기동물센터 환경미화 봉사활동", "봉사활동 내용2", "세종특별자치시 전동면 미륵당1길 117-16", "2024-07-16T10:00", "2024-07-17T10:00", "2024-07-13", "/images/volunteer/volunteer_default.jpg", 20, member7, 0);
            Volunteer volunteer3 = volunteerService.create("번식장 구조견 돌봄 봉사활동", "봉사활동 내용3", "충남 청양군 대치면 청산로 420", "2024-07-20T13:50", "2024-07-20T19:50", "2024-07-15", "/images/volunteer/volunteer_default.jpg", 20, member7, 0);
            Volunteer volunteer4 = volunteerService.create("유기동물 구조관련 세미나 및 환경 봉사활동", "봉사활동 내용4", "강원특별자치도 원주시 호저면 칠봉로 110-6", "2024-07-20T09:00", "2024-07-20T13:50", "2024-07-15", "/images/volunteer/volunteer_default.jpg", 20, member9, 0);
            Volunteer volunteer5 = volunteerService.create("동물물품 지원 봉사활동", "봉사활동 내용5", "충남 청양군 대치면 청산로 420", "2024-07-22T11:00", "2024-07-22T14:00", "2024-07-20", "/images/volunteer/volunteer_default.jpg", 20, member9, 0);
            Volunteer volunteer6 = volunteerService.create("사설보호소 지영이네 봉사활동", "봉사활동 내용6", "대전 유성구 금남구즉로 1234", "2024-08-01T13:00", "2024-08-01T19:00", "2024-07-30", "/images/volunteer/volunteer_default.jpg", 20, member8, 0);
            Volunteer volunteer7 = volunteerService.create("유기견 목욕시키기", "봉사활동 내용6", "강원특별자치도 원주시 호저면 칠봉로 110-6", "2024-08-05T08:50", "2024-08-06T09:50", "2024-08-02", "/images/volunteer/volunteer_default.jpg", 20, member7, 0);
            Volunteer volunteer8 = volunteerService.create("사설보호소 현철이네 봉사활동", "봉사활동 내용6", "충남 청양군 대치면 청산로 420", "2024-08-06T11:00", "2024-08-06T17:00", "2024-08-03", "/images/volunteer/volunteer_default.jpg", 20, member6, 0);
            Volunteer volunteer9 = volunteerService.create("번식장 구조견 돌봄 봉사활동", "봉사활동 내용6", "대전 유성구 금남구즉로 1234", "2024-08-10T09:00", "2024-08-10T17:50", "2024-08-05", "/images/volunteer/volunteer_default.jpg", 20, member9, 0);
            Volunteer volunteer10 = volunteerService.create("사설보호소 태우네 봉사활동", "봉사활동 내용6", "세종특별자치시 전동면 미륵당1길 117-16", "2024-08-15T07:50", "2024-08-15T14:50", "2024-08-10", "/images/volunteer/volunteer_default.jpg", 20, member2, 0);
            Volunteer volunteer11 = volunteerService.create("사설보호소 상민이네 봉사활동", "봉사활동 내용6", "강원특별자치도 원주시 호저면 칠봉로 110-6", "2024-08-16T16:00", "2024-08-17T15:50", "2024-08-12", "/images/volunteer/volunteer_default.jpg", 20, member2, 0);

            // 캘린더 샘플 만들기
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            LocalDateTime start1 = LocalDateTime.parse(volunteer1.getStartDate(), formatter);
            LocalDateTime end1 = LocalDateTime.parse(volunteer1.getEndDate(), formatter);
            LocalDateTime start2 = LocalDateTime.parse(volunteer2.getStartDate(), formatter);
            LocalDateTime end2 = LocalDateTime.parse(volunteer2.getEndDate(), formatter);
            LocalDateTime start3 = LocalDateTime.parse(volunteer3.getStartDate(), formatter);
            LocalDateTime end3 = LocalDateTime.parse(volunteer3.getEndDate(), formatter);
            LocalDateTime start4 = LocalDateTime.parse(volunteer4.getStartDate(), formatter);
            LocalDateTime end4 = LocalDateTime.parse(volunteer4.getEndDate(), formatter);
            LocalDateTime start5 = LocalDateTime.parse(volunteer5.getStartDate(), formatter);
            LocalDateTime end5 = LocalDateTime.parse(volunteer5.getEndDate(), formatter);
            LocalDateTime start6 = LocalDateTime.parse(volunteer6.getStartDate(), formatter);
            LocalDateTime end6 = LocalDateTime.parse(volunteer6.getEndDate(), formatter);
            LocalDateTime start7 = LocalDateTime.parse(volunteer7.getStartDate(), formatter);
            LocalDateTime end7 = LocalDateTime.parse(volunteer7.getEndDate(), formatter);
            LocalDateTime start8 = LocalDateTime.parse(volunteer8.getStartDate(), formatter);
            LocalDateTime end8 = LocalDateTime.parse(volunteer8.getEndDate(), formatter);
            LocalDateTime start9 = LocalDateTime.parse(volunteer9.getStartDate(), formatter);
            LocalDateTime end9 = LocalDateTime.parse(volunteer9.getEndDate(), formatter);
            LocalDateTime start10 = LocalDateTime.parse(volunteer10.getStartDate(), formatter);
            LocalDateTime end10 = LocalDateTime.parse(volunteer10.getEndDate(), formatter);
            LocalDateTime start11 = LocalDateTime.parse(volunteer11.getStartDate(), formatter);
            LocalDateTime end11 = LocalDateTime.parse(volunteer11.getEndDate(), formatter);


            calendarService.create(volunteer1.getTitle(), start1, end1, volunteer1);
            calendarService.create(volunteer2.getTitle(), start2, end2, volunteer2);
            calendarService.create(volunteer3.getTitle(), start3, end3, volunteer3);
            calendarService.create(volunteer4.getTitle(), start4, end4, volunteer4);
            calendarService.create(volunteer5.getTitle(), start5, end5, volunteer5);
            calendarService.create(volunteer6.getTitle(), start6, end6, volunteer6);
            calendarService.create(volunteer7.getTitle(), start7, end7, volunteer7);
            calendarService.create(volunteer8.getTitle(), start8, end8, volunteer8);
            calendarService.create(volunteer9.getTitle(), start9, end9, volunteer9);
            calendarService.create(volunteer10.getTitle(), start10, end10, volunteer10);
            calendarService.create(volunteer11.getTitle(), start11, end11, volunteer11);

            // 봉사후기 샘플
            volunteerReviewService.create("제목1", "내용1", 0, member4, "/images/volunteer/volunteer_default.jpg", null);
            volunteerReviewService.create("제목2", "내용2", 0, member5, "/images/volunteer/volunteer_default.jpg", null);
            volunteerReviewService.create("제목3", "내용3", 0, member10, "/images/volunteer/volunteer_default.jpg", null);
            volunteerReviewService.create("제목4", "내용4", 0, member4, "/images/volunteer/volunteer_default.jpg", null);
            volunteerReviewService.create("제목5", "내용5", 0, member5, "/images/volunteer/volunteer_default.jpg", null);
            volunteerReviewService.create("제목6", "내용6", 0, member10, "/images/volunteer/volunteer_default.jpg", null);
            volunteerReviewService.create("제목6", "내용6", 0, member10, "/images/volunteer/volunteer_default.jpg", null);
            volunteerReviewService.create("제목6", "내용6", 0, member10, "/images/volunteer/volunteer_default.jpg", null);
            volunteerReviewService.create("제목6", "내용6", 0, member10, "/images/volunteer/volunteer_default.jpg", null);


            productService.create("타이틀1","1 설명입니다",10000,3000);
            productService.create("타이틀2","2 설명입니다",20000,3000);
            productService.create("타이틀3","3 설명입니다",30000,3000);
            productService.create("타이틀4","4 설명입니다",40000,3000);

            // 후원 샘플
            Donation donation1 = new Donation();
            donation1.setAmount(10000000L);
            donation1.setDonor(member4); // Example: user1
            donation1.setRecipient(member6); // Example: company3
            donationService.save(donation1);

            Donation donation2 = new Donation();
            donation2.setAmount(20000000L);
            donation2.setDonor(member5); // Example: user2
            donation2.setRecipient(member7); // Example: company4
            donationService.save(donation2);

            Donation donation3 = new Donation();
            donation3.setAmount(30000000L);
            donation3.setDonor(member10); // Example: user3
            donation3.setRecipient(member8); // Example: company5
            donationService.save(donation3);

            articleService.create("Sample Title 1", "언제나 애니버스를 지지해 주시는 후원자님들께 안내 말씀 드립니다.\n" +
                    "\n" + "많은 후원자님들께서 애니버스 더봄센터에 후원해 주신 덕분에 애니버스 더봄센터는 무사히 완공이 되었습니다.\n" +
                    "\n" + "이에 2021년부터는 애니버스 더봄센터의 '벽돌 후원'과 '놀이터 후원' 항목을 '더봄센터건립' 항목으로 통합하여 운영할 예정입니다.\n" +
                    "\n" + "위와 같이 항목이 변경됨에 따라, 이전에 '벽돌 후원'과 '더봄센터 놀이터' 항목에 후원하셨던 후원자님들께서는 후원 항목의 명칭이 '더봄센터건립'으로 변경되더라도 혼동 없으시기를 바랍니다.\n" +
                    "\n" + "애니버스 더봄센터는 미완성 시설의 마무리를 위해 여전히 노력 중이며 후원자님들께서 더봄센터로 보내주시는 모든 후원금은 더봄센터의 모든 시설과 운영, 동물 돌봄을 위해 사용됩니다.\n" +
                    "\n" + "계속해서 애니버스 더봄센터에 많은 관심과 응원 부탁드립니다.\n" + "\n" + "애니버스에 후원해 주셔서 항상 감사합니다.", 0);
            articleService.create("Sample Title 2", "This is a sample content for the article.", 1);
            articleService.create("Sample Title 3", "This is a sample content for the article.", 2);
            articleService.create("Sample Title 4", "This is a sample content for the article.", 3);
            articleService.create("Sample Title 5", "This is a sample content for the article.", 4);
            articleService.create("Sample Title 6", "This is a sample content for the article.", 5);
            articleService.create("Sample Title 7", "This is a sample content for the article.", 6);
            articleService.create("Sample Title 8", "This is a sample content for the article.", 7);
            articleService.create("Sample Title 9", "This is a sample content for the article.", 8);
            articleService.create("Sample Title 10", "This is a sample content for the article.", 9);
            articleService.create("Sample Title 11", "This is a sample content for the article.", 10);

            qaService.create("Sample Title 1", "This is a sample content for the qa.",member1, 0);
            qaService.create("Sample Title 2", "This is a sample content for the qa.", member2,1);
            qaService.create("Sample Title 3", "This is a sample content for the qa.", member6,2);
            qaService.create("Sample Title 4", "This is a sample content for the qa.", member3,3);
            qaService.create("Sample Title 5", "This is a sample content for the qa.", member4,4);
            qaService.create("Sample Title 6", "This is a sample content for the qa.", member9,5);
            qaService.create("Sample Title 7", "This is a sample content for the qa.", member1,6);
            qaService.create("Sample Title 8", "This is a sample content for the qa.",member4, 7);
            qaService.create("Sample Title 9", "This is a sample content for the qa.", member3,8);
            qaService.create("Sample Title 10", "This is a sample content for the qa.", member2,9);
            qaService.create("Sample Title 11", "This is a sample content for the qa.",member2, 10);

        };
    }
}
