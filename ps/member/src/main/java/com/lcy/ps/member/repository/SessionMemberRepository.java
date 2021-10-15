package com.lcy.ps.member.repository;


import com.lcy.ps.member.domain.member.projectMember.ProjectMember;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.SessionMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionMemberRepository extends JpaRepository<SessionMember, Long> {
    List<SessionMember> findAllBySessionId(long sessionId);

}

