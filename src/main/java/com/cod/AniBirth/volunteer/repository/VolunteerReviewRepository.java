package com.cod.AniBirth.volunteer.repository;

import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerReviewRepository extends JpaRepository<VolunteerReview, Long> {

}
