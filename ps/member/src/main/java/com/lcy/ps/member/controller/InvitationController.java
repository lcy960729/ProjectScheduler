package com.lcy.ps.member.controller;

import com.lcy.ps.member.domain.invitation.Invitation;
import com.lcy.ps.member.domain.invitation.InvitationService;
import com.lcy.ps.member.dto.request.SendInvitationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Invitation> invite(Long userId,
                                             @RequestBody SendInvitationDTO sendInvitationDTO) {
        Invitation invitation = invitationService.invite(userId, sendInvitationDTO);
        return ResponseEntity.ok(invitation);
    }
}
