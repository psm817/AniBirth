package com.cod.AniBirth.volunteer.service;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;

    public Volunteer create(String title, String content, String location, String startDate,
                            String endDate, String deadLineDate, String thumbnailImg, int limit, Member member, int applicant) {
        Volunteer volunteer = Volunteer.builder()
                .title(title)
                .content(content)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .deadLineDate(deadLineDate)
                .thumbnailImg(thumbnailImg)
                .limit(limit)
                .register(member)
                .applicant(applicant)
                .build();

        return volunteerRepository.save(volunteer);
    }

    public Page<Volunteer> getAllVolunteer(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 4, Sort.by(sorts));

        return volunteerRepository.findAll(pageable);
    }
}
