package com.lcy.ps.main.api.v1.domain.project;

import com.lcy.ps.core.domain.member.permission.Permission;
import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.domain.project.ProjectMember;
import com.lcy.ps.core.domain.user.User;
import com.lcy.ps.core.dto.request.project.CreateProjectDTO;
import com.lcy.ps.core.dto.request.project.UpdateProjectDTO;
import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.core.repository.ProjectRepository;
import com.lcy.ps.core.repository.UserRepository;
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
