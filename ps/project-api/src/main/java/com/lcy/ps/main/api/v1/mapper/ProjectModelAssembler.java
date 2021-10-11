package com.lcy.ps.main.api.v1.mapper;


import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.dto.response.project.ProjectModel;
import com.lcy.ps.main.api.v1.controller.ProjectController;
import com.lcy.ps.main.api.v1.controller.SessionController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectModelAssembler extends RepresentationModelAssemblerSupport<Project, ProjectModel> {

    public ProjectModelAssembler() {
        super(ProjectController.class, ProjectModel.class);
    }

    @Override
    public ProjectModel toModel(Project project) {
        ProjectModel projectModel = ProjectModel.of(project);

        Long projectId = projectModel.getId();

        projectModel.add(linkTo(ProjectController.class).slash(projectId).withSelfRel());
        projectModel.add(linkTo(methodOn(ProjectController.class).update(null, projectId, null)).withRel("update"));
        projectModel.add(linkTo(methodOn(ProjectController.class).delete(null, projectId)).withRel("delete"));

        projectModel.add(linkTo(methodOn(ProjectController.class).invite(null, projectId, null)).withRel("invite_member"));

        projectModel.add(linkTo(methodOn(SessionController.class).create(null, projectId, null)).withRel("create_session"));

        return projectModel;
    }
}
