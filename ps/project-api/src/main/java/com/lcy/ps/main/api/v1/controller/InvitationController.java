package com.lcy.ps.main.api.v1.controller;

import com.lcy.ps.main.api.v1.domain.invitation.Invitation;
import com.lcy.ps.main.api.v1.domain.invitation.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/invitation",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaTypes.HAL_JSON_VALUE)
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @PostMapping(path = "/{id}/accept")
    public ResponseEntity<Invitation> accept(Long userId,
                                             @PathVariable("id") Long invitationId) {
        Invitation invitation = invitationService.accept(userId, invitationId);
        return ResponseEntity.ok(invitation);
    }

    @PostMapping(path = "/{id}/reject")
    public ResponseEntity<Invitation> reject(Long userId,
                                             @PathVariable("id") Long invitationId) {
        Invitation invitation = invitationService.reject(userId, invitationId);
        return ResponseEntity.ok(invitation);
    }
}
