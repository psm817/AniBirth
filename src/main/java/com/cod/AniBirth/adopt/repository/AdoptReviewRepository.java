package com.cod.AniBirth.adopt.repository;

import com.cod.AniBirth.adopt.entity.AdoptReview;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptReviewRepository extends JpaRepository<AdoptReview, Long> {

//    @Query("""
//            select distinct p
//            from AdoptReview p
//            where p.title like %:kw%
//            or p.content like %:kw%
//            """)
//    Page<AdoptReview> findAllByKeyword(String kw, Pageable pageable);
}
