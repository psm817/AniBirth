package com.cod.AniBirth.adopt.repository;

import com.cod.AniBirth.adopt.entity.AdoptReview;
import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptReviewRepository extends JpaRepository<AdoptReview, Long> {

    List<AdoptReview> findTop4ByOrderByCreateDateDesc();

    AdoptReview findFirstByIdLessThanOrderByIdDesc(Long id);

    AdoptReview findFirstByIdGreaterThanOrderByIdAsc(Long id);


//    @Query("""
//            select distinct p
//            from AdoptReview p
//            where p.title like %:kw%
//            or p.content like %:kw%
//            """)
//    Page<AdoptReview> findAllByKeyword(String kw, Pageable pageable);
}
