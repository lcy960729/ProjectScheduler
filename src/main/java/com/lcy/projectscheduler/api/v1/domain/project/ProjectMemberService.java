package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.repository.ProjectMemberRepository;
import com.lcy.projectscheduler.exception.NotRegisteredMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProjectMemberService {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Transactional
    public ProjectMember create(ProjectMember projectMember) {
        projectMember = projectMemberRepository.save(projectMember);
        Project project = projectMember.getProject();

        project.addMember(projectMember);

        return projectMember;
    }

    public ProjectMember get(long userId, long projectId) {
        return projectMemberRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(NotRegisteredMemberException::new);
    }
}
