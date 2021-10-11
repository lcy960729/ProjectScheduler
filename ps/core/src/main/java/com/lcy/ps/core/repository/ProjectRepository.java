package com.lcy.ps.core.repository;

import com.lcy.ps.core.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
