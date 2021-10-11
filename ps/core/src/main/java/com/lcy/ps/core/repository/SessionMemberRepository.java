package com.lcy.ps.core.repository;

import com.lcy.ps.core.domain.project.session.SessionMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionMemberRepository extends JpaRepository<SessionMember, Long> {
    Optional<SessionMember> findByUserIdAndSessionId(long userId, long sessionId);
}
