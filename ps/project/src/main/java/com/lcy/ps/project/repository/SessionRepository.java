package com.lcy.ps.project.repository;

import com.lcy.ps.project.domain.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByProjectId(long projectId);
}
