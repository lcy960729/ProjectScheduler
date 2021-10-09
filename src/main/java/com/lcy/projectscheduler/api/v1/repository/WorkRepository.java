package com.lcy.projectscheduler.api.v1.repository;

import com.lcy.projectscheduler.api.v1.domain.project.session.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllBySessionId(long sessionId);
}
