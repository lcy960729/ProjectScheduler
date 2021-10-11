package com.lcy.ps.core.repository;

import com.lcy.ps.core.domain.project.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    Optional<ProjectMember> findByUserIdAndProjectId(long userId, long projectId);

    List<ProjectMember> findAllByUserId(long userId);
}
