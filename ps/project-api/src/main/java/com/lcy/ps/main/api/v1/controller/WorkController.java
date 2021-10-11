package com.lcy.ps.main.api.v1.controller;

import com.lcy.ps.core.domain.project.session.work.Work;
import com.lcy.ps.core.dto.request.work.CreateWorkDTO;
import com.lcy.ps.core.dto.request.work.UpdateWorkDTO;
import com.lcy.ps.core.dto.response.work.WorkModel;
import com.lcy.ps.main.api.v1.mapper.WorkModelAssembler;
import com.lcy.ps.main.api.v1.domain.project.session.work.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/projects/{projectId}/sessions/{sessionId}/works",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaTypes.HAL_JSON_VALUE)

public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private WorkModelAssembler workModelAssembler;

    @PostMapping
    public ResponseEntity<WorkModel> create(Long userId,
                                            @PathVariable("projectId") Long projectId,
                                            @PathVariable("sessionId") Long sessionId,
                                            @RequestBody CreateWorkDTO createWorkDTO) {

        Work work = workService.create(userId, projectId, sessionId, createWorkDTO);

        WorkModel workModel = workModelAssembler.toModel(work);

        URI createdURI = workModel.getRequiredLink(IanaLinkRelations.SELF).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(workModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WorkModel> get(Long userId,
                                         @PathVariable("projectId") Long projectId,
                                         @PathVariable("sessionId") Long sessionId,
                                         @PathVariable("id") Long workId) {

        Work work = workService.get(userId, projectId, sessionId, workId);

        WorkModel workModel = workModelAssembler.toModel(work);

        return ResponseEntity.ok(workModel);
    }

    @GetMapping
    public ResponseEntity<List<WorkModel>> getAll(Long userId,
                                                  @PathVariable("projectId") Long projectId,
                                                  @PathVariable("sessionId") Long sessionId) {

        List<Work> works = workService.getAll(userId, projectId, sessionId);

        List<WorkModel> workModels = works.stream()
                .map(workModelAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(workModels);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<WorkModel> update(Long userId,
                                            @PathVariable("projectId") Long projectId,
                                            @PathVariable("sessionId") Long sessionId,
                                            @PathVariable("id") Long workId,
                                            @RequestBody UpdateWorkDTO updateWorkDTO) {

        Work work = workService.update(userId, projectId, sessionId, workId, updateWorkDTO);

        WorkModel workModel = workModelAssembler.toModel(work);

        return ResponseEntity.ok(workModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<WorkModel> delete(Long userId,
                                            @PathVariable("projectId") Long projectId,
                                            @PathVariable("sessionId") Long sessionId,
                                            @PathVariable("id") Long workId) {

        workService.delete(userId, projectId, sessionId, workId);

        return ResponseEntity.noContent().build();
    }
}
