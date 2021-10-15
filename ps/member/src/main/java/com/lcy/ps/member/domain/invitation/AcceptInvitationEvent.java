package com.lcy.ps.member.domain.invitation;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class AcceptInvitationEvent extends ApplicationEvent {

    private final long user;
    private final long project;

    public AcceptInvitationEvent(Object source, long user, long project) {
        super(source);
        this.user = user;
        this.project = project;
    }
}
