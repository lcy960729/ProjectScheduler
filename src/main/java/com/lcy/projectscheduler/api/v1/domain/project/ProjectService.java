package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.dto.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.repository.ProjectRepository;
import com.lcy.projectscheduler.api.v1.repository.UserRepository;
import com.lcy.projectscheduler.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProjectService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMemberService projectMemberService;

    @Transactional
    public Project create(CreateProjectDTO createProjectDTO) {
        User user = userRepository.findById(createProjectDTO.getManager())
                .orElseThrow(NotFoundEntityException::new);

        Project project = Project.of(createProjectDTO);
        project = projectRepository.save(project);

        ProjectMember superManager = ProjectMember.registerSuperManager(user, project);
        projectMemberService.create(superManager);

        return project;
    }

    public Project get(long userId, long projectId) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        return projectMember.getProject();
    }
}
