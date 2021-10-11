package com.lcy.ps.core.repository;

import com.lcy.ps.core.domain.project.session.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllBySessionId(long sessionId);
}
