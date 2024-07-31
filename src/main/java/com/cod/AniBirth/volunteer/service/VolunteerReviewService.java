package com.cod.AniBirth.volunteer.service;

import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import com.cod.AniBirth.volunteer.repository.VolunteerReviewRepository;
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
public class VolunteerReviewService {
    private final VolunteerReviewRepository volunteerReviewRepository;

    public void create(String title, String body, int hit, Member member, String thumbnailImg) {
        VolunteerReview volunteerReview = VolunteerReview.builder()
                .title(title)
                .body(body)
                .hit(hit)
                .writer(member)
                .thumbnailImg(thumbnailImg)
                .build();

        volunteerReviewRepository.save(volunteerReview);
    }

    public Page<VolunteerReview> getAll(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 6, Sort.by(sorts));

        return volunteerReviewRepository.findAll(pageable);
    }
}
