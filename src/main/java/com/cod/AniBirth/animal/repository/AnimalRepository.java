package com.cod.AniBirth.animal.repository;

import com.cod.AniBirth.adopt.entity.Adopt;
import com.cod.AniBirth.animal.AnimalSearchDTO;
import com.cod.AniBirth.animal.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

    @Query("""
        SELECT a FROM Animal a
        WHERE (:kw IS NULL OR a.species LIKE CONCAT('%', :kw, '%'))
        AND (:classification IS NULL OR a.classification LIKE CONCAT('%', :classification, '%'))
        AND (:gender IS NULL OR a.gender LIKE CONCAT('%', :gender, '%'))
        AND (:weight IS NULL OR a.weight LIKE CONCAT('%', :weight, '%'))
        AND (:age IS NULL OR a.age LIKE CONCAT('%', :age, '%'))
    """)
    Page<Animal> findAllByKeyword(
            Pageable pageable,
            @Param("kw") String kw,
            @Param("classification") String classification,
            @Param("gender") String gender,
            @Param("weight") String weight,
            @Param("age") String age
    );

//    Page<Animal> findAll(Specification<Animal> spec, Pageable pageable);

    List<Animal> findTop4ByOrderByCreateDateDesc();

}