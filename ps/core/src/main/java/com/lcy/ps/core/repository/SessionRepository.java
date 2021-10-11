package com.lcy.ps.core.repository;

import com.lcy.ps.core.domain.project.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByProjectId(long projectId);
}
