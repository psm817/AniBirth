package com.cod.AniBirth.member.repository;

import com.cod.AniBirth.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository <Member, Long> {


    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);


    Member findByUsernameAndEmail(String id, String email);

    Member findByEmail(String email);

}
