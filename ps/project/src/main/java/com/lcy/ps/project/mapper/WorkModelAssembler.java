package com.lcy.ps.project.mapper;


import com.lcy.ps.project.controller.WorkController;
import com.lcy.ps.project.domain.session.work.Work;
import com.lcy.ps.project.dto.response.work.WorkModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WorkModelAssembler extends RepresentationModelAssemblerSupport<Work, WorkModel> {

    public WorkModelAssembler() {
        super(WorkController.class, WorkModel.class);
    }

    @Override
    public WorkModel toModel(Work work) {
        WorkModel workModel = WorkModel.of(work);

        long workId = workModel.getId();
        long sessionId = work.getSession().getId();
        long projectId = work.getSession().getProject().getId();


//
        workModel.add(linkTo(methodOn(WorkController.class).get(null, null, sessionId, workId)).withSelfRel());
//        workModel.add(linkTo(methodOn(WorkController.class).update(null, workId, null)).withRel("update"));
//        workModel.add(linkTo(methodOn(WorkController.class).delete(null, workId)).withRel("delete"));

        return workModel;
    }
}
