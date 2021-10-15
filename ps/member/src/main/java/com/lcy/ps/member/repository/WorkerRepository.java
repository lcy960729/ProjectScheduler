package com.lcy.ps.member.repository;


import com.lcy.ps.member.domain.member.projectMember.sessionMember.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Worker findByWorkId(long workId);

}

