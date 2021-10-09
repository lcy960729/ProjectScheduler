package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;
import com.lcy.projectscheduler.authorization.domain.User;
import com.lcy.projectscheduler.api.v1.dto.request.project.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.dto.request.project.UpdateProjectDTO;
import com.lcy.projectscheduler.api.v1.repository.ProjectRepository;
import com.lcy.projectscheduler.authorization.repository.UserRepository;
import com.lcy.projectscheduler.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMemberService projectMemberService;

    @Transactional
    public Project create(long userId, CreateProjectDTO createProjectDTO) {
        User user = userRepository.findById(userId)
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

    public List<Project> getAll(long userId) {
        List<Project> projects = projectMemberService.getAll(userId).stream()
                .map(ProjectMember::getProject)
                .collect(Collectors.toList());

        return projects;
    }

    public Project update(long userId, long projectId, UpdateProjectDTO updateProjectDTO) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);

        projectMember.checkRegisteredAndPermission(Permission.UPDATE);

        Project project = projectMember.getProject();
        project.update(updateProjectDTO);

        project = projectRepository.save(project);

        return project;
    }

    public void delete(long userId, long projectId) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.DELETE);

        projectRepository.deleteById(projectId);
    }
}
