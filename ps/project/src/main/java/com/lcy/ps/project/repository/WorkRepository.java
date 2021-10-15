package com.lcy.ps.project.repository;

import com.lcy.ps.project.domain.session.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllBySessionId(long sessionId);
}
