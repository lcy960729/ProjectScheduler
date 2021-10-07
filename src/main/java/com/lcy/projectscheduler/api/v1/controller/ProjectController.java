package com.lcy.projectscheduler.api.v1.controller;

import com.lcy.projectscheduler.api.v1.domain.invitation.Invitation;
import com.lcy.projectscheduler.api.v1.domain.invitation.InvitationService;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectService;
import com.lcy.projectscheduler.api.v1.dto.request.invitation.SendInvitationDTO;
import com.lcy.projectscheduler.api.v1.dto.request.project.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.dto.request.project.UpdateProjectDTO;
import com.lcy.projectscheduler.api.v1.dto.response.project.ProjectModel;
import com.lcy.projectscheduler.api.v1.dto.response.project.ProjectModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/projects",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaTypes.HAL_JSON_VALUE)
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ProjectModelAssembler projectModelAssembler;

    @PostMapping
    public ResponseEntity<ProjectModel> create(Long userId,
                                               @RequestBody CreateProjectDTO createProjectDTO) {

        Project project = projectService.create(userId, createProjectDTO);

        ProjectModel projectModel = projectModelAssembler.toModel(project);

        URI createdURI = projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(projectModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectModel> get(Long userId,
                                            @PathVariable("id") Long projectId) {
        Project project = projectService.get(userId, projectId);

        ProjectModel projectModel = projectModelAssembler.toModel(project);

        return ResponseEntity.ok(projectModel);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjectModel> update(Long userId,
                                               @PathVariable("id") Long projectId,
                                               @RequestBody UpdateProjectDTO updateProjectDTO) {
        Project project = projectService.update(userId, projectId, updateProjectDTO);

        ProjectModel projectModel = projectModelAssembler.toModel(project);

        return ResponseEntity.ok(projectModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProjectModel> delete(Long userId,
                                               @PathVariable("id") Long projectId) {
        projectService.delete(userId, projectId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/invite")
    public ResponseEntity<Invitation> invite(Long userId,
                                             @PathVariable("id") Long projectId,
                                             @RequestBody SendInvitationDTO sendInvitationDTO) {
        Invitation invitation = invitationService.invite(0L, sendInvitationDTO.getReceiverId(), projectId);
        return ResponseEntity.ok(invitation);
    }
}
