package com.lcy.ps.main.api.v1.domain.project;

import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.domain.project.ProjectMember;
import com.lcy.ps.core.exception.NotRegisteredMemberException;
import com.lcy.ps.core.repository.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<ProjectMember> getAll(long userId) {
        return projectMemberRepository.findAllByUserId(userId);
    }
}
