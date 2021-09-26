package com.lcy.projectscheduler.api.v1.repository;

import com.lcy.projectscheduler.api.v1.domain.project.session.work.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
