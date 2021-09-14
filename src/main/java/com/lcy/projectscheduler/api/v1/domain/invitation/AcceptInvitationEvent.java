package com.lcy.projectscheduler.api.v1.domain.invitation;

import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class AcceptInvitationEvent extends ApplicationEvent {

    private final User user;
    private final Project project;

    public AcceptInvitationEvent(Object source, User user, Project project) {
        super(source);
        this.user = user;
        this.project = project;
    }
}
