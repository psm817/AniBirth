package com.cod.AniBirth.member.repository;

import com.cod.AniBirth.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository <Member, Long> {

}
