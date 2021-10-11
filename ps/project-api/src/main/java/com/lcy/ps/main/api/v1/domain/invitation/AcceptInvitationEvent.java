package com.lcy.ps.main.api.v1.domain.invitation;

import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.domain.user.User;
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
