package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.dto.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.repository.ProjectMemberRepository;
import com.lcy.projectscheduler.api.v1.repository.ProjectRepository;
import com.lcy.projectscheduler.api.v1.repository.UserRepository;
import com.lcy.projectscheduler.exception.HasNotPermissionException;
import com.lcy.projectscheduler.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

        Project newProject = Project.create(createProjectDTO);
        newProject = projectRepository.save(newProject);

        ProjectMember superManager = ProjectMember.registerSuperManager(user, newProject);
        projectMemberService.create(superManager);

        return newProject;
    }
}
