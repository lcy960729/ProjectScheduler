package com.lcy.ps.project.controller;

import com.lcy.ps.project.domain.session.work.Work;
import com.lcy.ps.project.domain.session.work.WorkService;
import com.lcy.ps.project.dto.request.work.CreateWorkDTO;
import com.lcy.ps.project.dto.request.work.UpdateWorkDTO;
import com.lcy.ps.project.dto.response.work.WorkModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/projects/{projectId}/sessions/{sessionId}/works",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaTypes.HAL_JSON_VALUE)

public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping
    public ResponseEntity<WorkModel> create(@RequestParam("memberId") Long memberId,
                                            @PathVariable("projectId") Long projectId,
                                            @PathVariable("sessionId") Long sessionId,
                                            @RequestBody CreateWorkDTO createWorkDTO) {

        WorkModel workModel = workService.create(memberId, sessionId, createWorkDTO);

        URI createdURI = workModel.getRequiredLink(IanaLinkRelations.SELF).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(workModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WorkModel> get(@RequestParam("memberId") Long memberId,
                                         @PathVariable("projectId") Long projectId,
                                         @PathVariable("sessionId") Long sessionId,
                                         @PathVariable("id") Long workId) {

        WorkModel workModel = workService.get(memberId, workId);

        return ResponseEntity.ok(workModel);
    }

    @GetMapping
    public ResponseEntity<List<WorkModel>> getAll(@RequestParam("memberId") Long memberId,
                                                  @PathVariable("projectId") Long projectId,
                                                  @PathVariable("sessionId") Long sessionId) {

        List<WorkModel> workModels = workService.getAll(sessionId);

        return ResponseEntity.ok(workModels);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<WorkModel> update(@RequestParam("memberId") Long memberId,
                                            @PathVariable("projectId") Long projectId,
                                            @PathVariable("sessionId") Long sessionId,
                                            @PathVariable("id") Long workId,
                                            @RequestBody UpdateWorkDTO updateWorkDTO) {

        WorkModel workModel = workService.update(memberId, workId, updateWorkDTO);

        return ResponseEntity.ok(workModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<WorkModel> delete(@RequestParam("memberId") Long memberId,
                                            @PathVariable("projectId") Long projectId,
                                            @PathVariable("sessionId") Long sessionId,
                                            @PathVariable("id") Long workId) {

        workService.delete(memberId, workId);

        return ResponseEntity.noContent().build();
    }
}
