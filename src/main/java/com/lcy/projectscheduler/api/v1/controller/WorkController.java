package com.lcy.projectscheduler.api.v1.controller;

import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import com.lcy.projectscheduler.api.v1.domain.project.session.work.Work;
import com.lcy.projectscheduler.api.v1.domain.project.session.work.WorkService;
import com.lcy.projectscheduler.api.v1.dto.request.work.CreateWorkDTO;
import com.lcy.projectscheduler.api.v1.dto.request.session.UpdateSessionDTO;
import com.lcy.projectscheduler.api.v1.dto.request.work.UpdateWorkDTO;
import com.lcy.projectscheduler.api.v1.dto.response.session.SessionModel;
import com.lcy.projectscheduler.api.v1.dto.response.work.WorkModel;
import com.lcy.projectscheduler.api.v1.dto.response.work.WorkModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/projects/{projectId}/sessions/{sessionId}/works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private WorkModelAssembler workModelAssembler;

    @PostMapping
    public ResponseEntity<WorkModel> create(@PathVariable("projectId") Long projectId,
                                            @PathVariable("sessionId") Long sessionId,
                                            @RequestBody CreateWorkDTO createWorkDTO) {

        Work work = workService.create(0L, projectId, sessionId, createWorkDTO);

        WorkModel workModel = workModelAssembler.toModel(work);

        URI createdURI = workModel.getRequiredLink(IanaLinkRelations.SELF).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(workModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WorkModel> get(@PathVariable("projectId") Long projectId,
                                         @PathVariable("sessionId") Long sessionId,
                                         @PathVariable("id") Long workId) {

        Work work = workService.get(0L, projectId, sessionId, workId);

        WorkModel workModel = workModelAssembler.toModel(work);

        return ResponseEntity.ok(workModel);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<WorkModel> update(@PathVariable("projectId") Long projectId,
                                            @PathVariable("sessionId") Long sessionId,
                                            @PathVariable("id") Long workId,
                                            @RequestBody UpdateWorkDTO updateWorkDTO) {

        Work work = workService.update(0L, projectId, sessionId, workId, updateWorkDTO);

        WorkModel workModel = workModelAssembler.toModel(work);

        return ResponseEntity.ok(workModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SessionModel> delete(@PathVariable("projectId") Long projectId,
                                               @PathVariable("sessionId") Long sessionId,
                                               @PathVariable("id") Long workId) {

        workService.delete(0L, projectId, sessionId, workId);

        return ResponseEntity.noContent().build();
    }
}
