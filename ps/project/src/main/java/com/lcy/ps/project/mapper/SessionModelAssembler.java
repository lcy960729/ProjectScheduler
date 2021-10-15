package com.lcy.ps.project.mapper;


import com.lcy.ps.project.controller.SessionController;
import com.lcy.ps.project.controller.WorkController;
import com.lcy.ps.project.domain.session.Session;
import com.lcy.ps.project.dto.response.session.SessionModel;
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

        sessionModel.add(linkTo(methodOn(SessionController.class).get(null, null, sessionId)).withSelfRel());
//        sessionModel.add(linkTo(methodOn(SessionController.class).update(null, sessionId, null)).withRel("update"));
//        sessionModel.add(linkTo(methodOn(SessionController.class).delete(null, sessionId)).withRel("delete"));
//        sessionModel.add(linkTo(methodOn(WorkController.class).create(null, projectId, sessionId, null)).withRel("create_work"));

        return sessionModel;
    }


}
