package com.lcy.ps.project.domain;

import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.integrate.domain.member.GetMemberService;
import com.lcy.ps.integrate.domain.member.Permission;
import com.lcy.ps.integrate.domain.member.RegisterMemberService;
import com.lcy.ps.integrate.dto.MemberModel;
import com.lcy.ps.project.annotation.MemberID;
import com.lcy.ps.project.annotation.NeedPermission;
import com.lcy.ps.project.dto.request.project.CreateProjectDto;
import com.lcy.ps.project.dto.request.project.UpdateProjectDto;
import com.lcy.ps.project.dto.response.project.ProjectModel;
import com.lcy.ps.project.mapper.ProjectModelAssembler;
import com.lcy.ps.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectModelAssembler projectModelAssembler;

    @Autowired
    private GetMemberService getMemberService;

    @Autowired
    private RegisterMemberService registerMemberService;

    @Transactional
    public ProjectModel create(CreateProjectDto createProjectDTO) {
        Project project = Project.of(createProjectDTO);
        project = projectRepository.save(project);

        Long managerId = createProjectDTO.getManagerId();
        MemberModel manager = registerMemberService.registerProjectManager(managerId, project.getId());

        ProjectModel projectModel = projectModelAssembler.toModel(project);
        projectModel.getMembers().add(manager);

        return projectModel;
    }

    @Transactional(readOnly = true)
    @NeedPermission(value = Permission.READ)
    public ProjectModel get(@MemberID long memberId, long projectId) {

        Project project = getProject(projectId);

        return toProjectModel(project);
    }

    public List<Project> getAll(long userId) {
//        return projectMemberService.getAll(userId).stream()
//                .map(ProjectMember::getProject)
//                .collect(Collectors.toList());
        return null;
    }

    @Transactional
    @NeedPermission(Permission.UPDATE)
    public ProjectModel update(@MemberID long memberId, long projectId, UpdateProjectDto updateProjectDTO) {
        Project project = getProject(projectId);

        project.update(updateProjectDTO);

        project = projectRepository.save(project);

        return toProjectModel(project);
    }

    @Transactional
    @NeedPermission(Permission.DELETE)
    public void delete(@MemberID long memberId, long projectId) {
        projectRepository.deleteById(projectId);
    }


    private Project getProject(long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(NotFoundEntityException::new);
    }

    private ProjectModel toProjectModel(Project project) {
        List<MemberModel> members = getMemberService.getProjectMembers(project.getId());

        ProjectModel projectModel = projectModelAssembler.toModel(project);
        projectModel.setMembers(members);

        return projectModel;
    }
}
