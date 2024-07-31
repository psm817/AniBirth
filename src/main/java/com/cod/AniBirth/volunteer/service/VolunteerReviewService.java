package com.cod.AniBirth.volunteer.service;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import com.cod.AniBirth.volunteer.repository.VolunteerReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerReviewService {
    private final VolunteerReviewRepository volunteerReviewRepository;

    public void create(String title, String body, int hit, Member member) {
        VolunteerReview volunteerReview = VolunteerReview.builder()
                .title(title)
                .body(body)
                .hit(hit)
                .writer(member)
                .build();

        volunteerReviewRepository.save(volunteerReview);
    }

    public List<VolunteerReview> getAll() {
        return volunteerReviewRepository.findAll();
    }
}
