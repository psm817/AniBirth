package com.cod.AniBirth.adopt.service;

import com.cod.AniBirth.adopt.AdoptForm;
import com.cod.AniBirth.adopt.entity.Adopt;
import com.cod.AniBirth.adopt.entity.AdoptApply;
import com.cod.AniBirth.adopt.repository.AdoptApplyRepository;
import com.cod.AniBirth.adopt.repository.AdoptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdoptService {
    private final AdoptRepository adoptRepository;
    private final AdoptApplyRepository adoptApplyRepository;

    public void apply(String name, String phone, String email, String age, String company, String sample6Postcode, String sample6Address, String sample6DetailAddress, String sample6ExtraAddress, boolean isGender, boolean isMarried) {
        // boolean 값을 String 값으로 변환
        String gender = isGender ? "남자" : "여자";
        String marriedStatus = isMarried ? "기혼" : "미혼";

        AdoptApply adoptApply = AdoptApply.builder()
                .name(name)
                .phone(phone)
                .age(age)
                .email(email)
                .company(company)
                .postCode(sample6Postcode)
                .address(sample6Address)
                .detailAddress(sample6DetailAddress)
                .extraAddress(sample6ExtraAddress)
                .gender(gender)
                .marriedStatus(marriedStatus)
                .build();

        adoptApplyRepository.save(adoptApply);

    }


//    public void apply(String name, String phone) {
//        Adopt a = Adopt.builder()
//                .name(name)
//                .phone(phone)
//                .build();
//        adoptRepository.save(a);
//    }


}
