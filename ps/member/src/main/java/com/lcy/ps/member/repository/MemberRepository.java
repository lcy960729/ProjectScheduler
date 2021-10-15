package com.lcy.ps.member.repository;


import com.lcy.ps.member.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

