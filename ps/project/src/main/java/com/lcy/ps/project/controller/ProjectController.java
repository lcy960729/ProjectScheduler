package com.lcy.ps.project.controller;


import com.lcy.ps.project.domain.ProjectService;
import com.lcy.ps.project.dto.request.project.CreateProjectDto;
import com.lcy.ps.project.dto.request.project.UpdateProjectDto;
import com.lcy.ps.project.dto.response.project.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/projects",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaTypes.HAL_JSON_VALUE)
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectModel> create(@RequestBody CreateProjectDto createProjectDTO) {

        ProjectModel projectModel = projectService.create(createProjectDTO);

        URI createdURI = projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri();

        return ResponseEntity.created(createdURI).body(projectModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectModel> get(@PathVariable("id") Long projectId,
                                            @RequestParam("memberId") Long memberId) {

        ProjectModel projectModel = projectService.get(memberId, projectId);

        return ResponseEntity.ok(projectModel);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectModel>> getAll(@RequestParam("memberId") Long memberId) {
//        List<Project> projects = projectService.getAll(userId);
//
//        List<ProjectModel> projectModels = projects.stream()
//                .map(project -> projectModelAssembler.toModel(project))
//                .collect(Collectors.toList());

        return ResponseEntity.ok(null);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjectModel> update(@PathVariable("id") Long projectId,
                                               @RequestParam("memberId") Long memberId,
                                               @RequestBody UpdateProjectDto updateProjectDTO) {

        ProjectModel projectModel = projectService.update(memberId, projectId, updateProjectDTO);

        return ResponseEntity.ok(projectModel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProjectModel> delete(@RequestParam("memberId") Long memberId,
                                               @PathVariable("id") Long projectId) {
        projectService.delete(memberId, projectId);

        return ResponseEntity.noContent().build();
    }
}
