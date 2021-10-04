package com.lcy.projectscheduler.api.v1.repository;

import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.domain.project.session.SessionMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionMemberRepository extends JpaRepository<SessionMember, Long> {
    Optional<SessionMember> findByUserIdAndSessionId(long userId, long sessionId);
}
