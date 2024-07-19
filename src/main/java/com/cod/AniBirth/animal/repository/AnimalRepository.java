package com.cod.AniBirth.animal.repository;

import com.cod.AniBirth.animal.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    public Optional<Animal> findByName(String name);
}
