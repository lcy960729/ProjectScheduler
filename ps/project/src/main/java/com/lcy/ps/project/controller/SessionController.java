package com.lcy.ps.project.controller;

import com.lcy.ps.project.domain.session.Session;
import com.lcy.ps.project.domain.session.SessionService;
import com.lcy.ps.project.dto.request.session.CreateSessionDTO;
import com.lcy.ps.project.dto.request.session.UpdateSessionDTO;
import com.lcy.ps.project.dto.response.session.SessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/projects/{projectId}/sessions",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaTypes.HAL_JSON_VALUE)
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionModel> create(@RequestParam("memberId") Long memberId,
                                               @PathVariable("projectId") Long projectId,
                                               @RequestBody CreateSessionDTO createSessionDTO) {

        SessionModel sessionModel = sessionService.create(memberId, projectId, createSessionDTO);

        URI createdURI = sessionModel.getRequiredLink(IanaLinkRelations.SELF).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(sessionModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SessionModel> get(@RequestParam("memberId") Long memberId,
                                            @PathVariable("projectId") Long projectId,
                                            @PathVariable("id") Long sessionId) {

        SessionModel sessionModel = sessionService.getSession(memberId, sessionId);

        return ResponseEntity.ok(sessionModel);
    }

    @GetMapping
    public ResponseEntity<List<SessionModel>> getAll(@RequestParam("memberId") Long memberId,
                                                     @PathVariable("projectId") Long projectId) {

        List<SessionModel> sessionModelList = sessionService.getAll(memberId, projectId);

        return ResponseEntity.ok(sessionModelList);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SessionModel> update(@RequestParam("memberId") Long memberId,
                                               @PathVariable("projectId") Long projectId,
                                               @PathVariable("id") Long sessionId,
                                               @RequestBody UpdateSessionDTO updateSessionDTO) {

        SessionModel sessionModel = sessionService.update(memberId, sessionId, updateSessionDTO);

        return ResponseEntity.ok(sessionModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SessionModel> delete(@RequestParam("memberId") Long memberId,
                                               @PathVariable("id") Long sessionId) {

        sessionService.delete(memberId, sessionId);

        return ResponseEntity.noContent().build();
    }
}
