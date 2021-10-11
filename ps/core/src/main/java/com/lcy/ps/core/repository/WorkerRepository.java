package com.lcy.ps.core.repository;

import com.lcy.ps.core.domain.project.session.work.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Optional<Worker> findByUserIdAndWorkId(long userId, long workId);
}
