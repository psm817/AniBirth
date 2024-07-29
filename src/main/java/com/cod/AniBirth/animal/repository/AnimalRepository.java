package com.cod.AniBirth.animal.repository;

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
            select distinct p
            from Animal p
            where p.species like %:kw%
            or p.gender like %:kw%
            """)
    Page<Animal> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

    Page<Animal> findAllByCategory_Id(Long classificationId, Pageable pageable);
    Page<Animal> findAll(Specification<Animal> spec, Pageable pageable);
}
