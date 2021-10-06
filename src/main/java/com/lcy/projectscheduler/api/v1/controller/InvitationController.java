package com.lcy.projectscheduler.api.v1.controller;

import com.lcy.projectscheduler.api.v1.domain.invitation.Invitation;
import com.lcy.projectscheduler.api.v1.domain.invitation.InvitationService;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectService;
import com.lcy.projectscheduler.api.v1.dto.request.invitation.SendInvitationDTO;
import com.lcy.projectscheduler.api.v1.dto.request.project.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.dto.request.project.UpdateProjectDTO;
import com.lcy.projectscheduler.api.v1.dto.response.project.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.hateoas.HateoasProperties;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/invitation",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaTypes.HAL_JSON_VALUE)
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @PostMapping(path = "/{id}/accept")
    public ResponseEntity<Invitation> accept(@PathVariable("id") Long invitationId) {
        Invitation invitation = invitationService.accept(2L, invitationId);
        return ResponseEntity.ok(invitation);
    }

    @PostMapping(path = "/{id}/reject")
    public ResponseEntity<Invitation> reject(@PathVariable("id") Long invitationId) {
        Invitation invitation = invitationService.reject(2L, invitationId);
        return ResponseEntity.ok(invitation);
    }
}
