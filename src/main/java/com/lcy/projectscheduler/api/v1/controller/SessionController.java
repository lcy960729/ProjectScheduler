package com.lcy.projectscheduler.api.v1.controller;

import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import com.lcy.projectscheduler.api.v1.domain.project.session.SessionService;
import com.lcy.projectscheduler.api.v1.dto.request.session.AddMembersToSessionDTO;
import com.lcy.projectscheduler.api.v1.dto.request.session.CreateSessionDTO;
import com.lcy.projectscheduler.api.v1.dto.request.session.UpdateSessionDTO;
import com.lcy.projectscheduler.api.v1.dto.response.session.SessionModel;
import com.lcy.projectscheduler.api.v1.dto.response.session.SessionModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/projects/{projectId}/sessions",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaTypes.HAL_JSON_VALUE)
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionModelAssembler sessionModelAssembler;

    @PostMapping
    public ResponseEntity<SessionModel> create(Long userId,
                                               @PathVariable("projectId") Long projectId,
                                               @RequestBody CreateSessionDTO createSessionDTO) {

        Session session = sessionService.create(userId, projectId, createSessionDTO);

        SessionModel sessionModel = sessionModelAssembler.toModel(session);

        URI createdURI = sessionModel.getRequiredLink(IanaLinkRelations.SELF).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(sessionModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SessionModel> get(Long userId,
                                            @PathVariable("projectId") Long projectId,
                                            @PathVariable("id") Long sessionId) {

        Session session = sessionService.get(userId, projectId, sessionId);

        SessionModel sessionModel = sessionModelAssembler.toModel(session);

        return ResponseEntity.ok(sessionModel);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SessionModel> update(Long userId,
                                               @PathVariable("projectId") Long projectId,
                                               @PathVariable("id") Long sessionId,
                                               @RequestBody UpdateSessionDTO updateSessionDTO) {

        Session session = sessionService.update(userId, projectId, sessionId, updateSessionDTO);

        SessionModel sessionModel = sessionModelAssembler.toModel(session);

        return ResponseEntity.ok(sessionModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SessionModel> delete(Long userId,
                                               @PathVariable("projectId") Long projectId,
                                               @PathVariable("id") Long sessionId) {

        sessionService.delete(userId, projectId, sessionId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}/addUsers")
    public ResponseEntity<SessionModel> addUsers(Long userId,
                                                 @PathVariable("projectId") Long projectId,
                                                 @PathVariable("id") Long sessionId,
                                                 @RequestBody AddMembersToSessionDTO addMembersToSessionDTO) {

        Session session = sessionService.addMembers(userId, projectId, sessionId, addMembersToSessionDTO);

        SessionModel sessionModel = sessionModelAssembler.toModel(session);

        return ResponseEntity.ok(sessionModel);
    }
}
