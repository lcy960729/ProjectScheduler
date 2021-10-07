package com.lcy.projectscheduler.api.v1.dto.response.work;

import com.lcy.projectscheduler.api.v1.controller.SessionController;
import com.lcy.projectscheduler.api.v1.controller.WorkController;
import com.lcy.projectscheduler.api.v1.domain.project.session.work.Work;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WorkModelAssembler extends RepresentationModelAssemblerSupport<Work, WorkModel> {

    public WorkModelAssembler() {
        super(SessionController.class, WorkModel.class);
    }

    @Override
    public WorkModel toModel(Work work) {
        WorkModel workModel = WorkModel.of(work);

        long workId = workModel.getId();
        long sessionId = work.getSession().getId();
        long projectId = work.getSession().getProject().getId();

        workModel.add(linkTo(methodOn(WorkController.class).get(null,projectId, sessionId, workId)).withSelfRel());
        workModel.add(linkTo(methodOn(WorkController.class).update(null,projectId, sessionId, workId, null)).withRel("update"));
        workModel.add(linkTo(methodOn(WorkController.class).delete(null,projectId, sessionId, workId)).withRel("delete"));

        return workModel;
    }
}
