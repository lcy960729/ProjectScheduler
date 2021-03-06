package com.lcy.projectscheduler.api.v1.dto.response.session;

import com.lcy.projectscheduler.api.v1.controller.SessionController;
import com.lcy.projectscheduler.api.v1.controller.WorkController;
import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SessionModelAssembler extends RepresentationModelAssemblerSupport<Session, SessionModel> {

    public SessionModelAssembler() {
        super(SessionController.class, SessionModel.class);
    }

    @Override
    public SessionModel toModel(Session session) {
        SessionModel sessionModel = SessionModel.of(session);

        long sessionId = sessionModel.getId();
        long projectId = session.getProject().getId();

        sessionModel.add(linkTo(methodOn(SessionController.class).get(null, projectId, sessionId)).withSelfRel());
        sessionModel.add(linkTo(methodOn(SessionController.class).update(null, projectId, sessionId, null)).withRel("update"));
        sessionModel.add(linkTo(methodOn(SessionController.class).delete(null, projectId, sessionId)).withRel("delete"));
        sessionModel.add(linkTo(methodOn(WorkController.class).create(null, projectId, sessionId, null)).withRel("create_work"));

        return sessionModel;
    }
}
