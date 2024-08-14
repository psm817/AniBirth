package com.cod.AniBirth.volunteer.repository;

import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerReviewRepository extends JpaRepository<VolunteerReview, Long> {

    VolunteerReview findFirstByIdLessThanOrderByIdDesc(Long id);

    VolunteerReview findFirstByIdGreaterThanOrderByIdAsc(Long id);

    List<VolunteerReview> findTop4ByOrderByCreateDateDesc();
}
