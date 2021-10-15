package com.lcy.ps.member.repository;


import com.lcy.ps.member.domain.member.Member;
import com.lcy.ps.member.domain.member.projectMember.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    List<ProjectMember> findAllByProjectId(long projectId);

}

