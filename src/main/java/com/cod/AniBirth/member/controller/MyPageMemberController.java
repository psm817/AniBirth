package com.cod.AniBirth.member.controller;

import com.cod.AniBirth.account.entity.Account;
import com.cod.AniBirth.account.service.AccountService;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.animal.service.AnimalService;
import com.cod.AniBirth.donation.entity.Donation;
import com.cod.AniBirth.donation.service.DonationService;
import com.cod.AniBirth.email.service.EmailService;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.repository.MemberRepository;
import com.cod.AniBirth.member.service.MemberService;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.entity.VolunteerApplication;
import com.cod.AniBirth.volunteer.service.VolunteerApplicationService;
import com.cod.AniBirth.volunteer.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member/myPage")
@RequiredArgsConstructor
@Slf4j
public class MyPageMemberController {
    private final MemberService memberService;
    private final AccountService accountService;
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final AnimalService animalService;
    private final DonationService donationService;
    private final VolunteerService volunteerService;
    private final VolunteerApplicationService volunteerApplicationService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myProfile")
    public String myPage(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);
        List<Member> memberList = memberService.getAllMember();

        model.addAttribute("member", member);
        model.addAttribute("account", account);
        model.addAttribute("memberList", memberList);

        return "member/myPage/myProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/approve")
    public String approve(@RequestParam(value = "username", defaultValue = "") String username ) {
        Member member = memberService.findByUsername(username);
        member.setIsActive(1);
        memberRepository.save(member);

        String subject = "애니버스 - 회원가입 승인 완료";

        String body = String.format(
                "안녕하세요, <b>%s</b>님<br><br>"+
                        "애니버스 회원가입 승인이 완료되었습니다. 저희는 유기동물 봉사, 입양, 후원을 통해 따뜻한 사회를 만들어 나가는 커뮤티니 사이트입니다.<br><br>"+
                        "이제 <b>%s</b>님도 마켓을 개설하여 상품을 등록하실 수 있습니다.<br><br>"+
                        "<b>%s</b>님의 참여와 관심이 유기동물들에게 큰 힘이 됩니다. 앞으로 저희 애니버스와 함께 따뜻한 손길을 나누며 더 나은 세상을 만들어 나가길 바랍니다.<br><br>"+
                        "언제든지 궁금한 점이나 도움이 필요하시면 애니버스에 연락 부탁드립니다. 항상 <b>%s</b>님의 의견에 귀 기울이며 더욱 발전해 나가겠습니다.<br><br>"+
                        "다시 한번 환영하며, 감사합니다.<br><br>"+
                        "따뜻한 하루 보내세요.<br><br>"+
                        "애니버스 팀 드림<br>"+
                        "고객지원 이메일 주소 : 5004jse@gmail.com<br>"+
                        "웹 사이트 주소 : http://localhost:8040",
                member.getUsername(), member.getUsername(), member.getUsername(), member.getUsername()
                );

        emailService.send(member.getEmail(), subject, body);

        return "redirect:/member/myPage/myProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/volunteer")
    public String myVolunteer(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());

        // 일반회원 봉사신청 리스트
        List<VolunteerApplication> volunteerApplicationList = volunteerApplicationService.getAllByMember(member);

        // 중간관리자 봉사등록 리스트
        List<Volunteer> volunteerList = volunteerService.getVolunteerByMember(member);

        // 최고관리자 봉사등록 리스트
        List<Volunteer> volunteerAllList = volunteerService.getAllVolunteer();

        model.addAttribute("member", member);
        model.addAttribute("volunteerApplicationList", volunteerApplicationList);
        model.addAttribute("volunteerList", volunteerList);
        model.addAttribute("volunteerAllList", volunteerAllList);

        return "member/myPage/volunteer";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/adopt")
    public String myAdopt(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        List<Animal> animalList = animalService.findAll();

        model.addAttribute("member", member);
        model.addAttribute("animalList", animalList);

        return "member/myPage/adopt";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/donation")
    public String myDonation(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);

        // 일반 회원 기부 리스트
        List<Donation> donationList = donationService.getDonationsByDonor(member);
        Long donationCount = (long) donationList.size();

        // 중간 관리자 및 최고 관리자가 받은 기부 리스트
        List<Donation> receivedDonationList = donationService.getDonationsReceivedByMember(member);

        // 기부 리스트를 저장할 리스트
        List<Donation> allDonations = new ArrayList<>();

        // 관리자 권한에 따른 기부 리스트 설정
        if (member.getAuthority() == 0) { // 최고 관리자
            allDonations = donationService.findAll();
            donationCount = (long) allDonations.size();
        } else if (member.getAuthority() == 1) { // 중간 관리자
            allDonations = receivedDonationList;
            donationCount = (long) receivedDonationList.size();
        } else { // 일반 회원
            donationCount = (long) donationList.size();
        }

        model.addAttribute("member", member);
        model.addAttribute("account", account);
        model.addAttribute("donationList", donationList);
        model.addAttribute("receivedDonationList", receivedDonationList);
        model.addAttribute("allDonations", allDonations);
        model.addAttribute("donationCount", donationCount);

        return "member/myPage/donation";
    }






    @PreAuthorize("isAuthenticated()")
    @GetMapping("/market")
    public String myMarket(Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Account account = accountService.findByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("account", account);

        return "member/myPage/market";
    }
}
