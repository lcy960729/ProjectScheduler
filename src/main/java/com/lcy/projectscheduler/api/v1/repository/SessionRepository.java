package com.lcy.projectscheduler.api.v1.repository;

import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByProjectId(long projectId);

}
