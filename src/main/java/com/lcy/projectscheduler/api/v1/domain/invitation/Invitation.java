package com.lcy.projectscheduler.api.v1.domain.invitation;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Invitation extends BaseEntity {
    @JoinColumn
    @ManyToOne
    private User receiver;

    @JoinColumn
    @ManyToOne
    private User sender;

    @JoinColumn
    @ManyToOne
    private Project project;

    @Column
    private InvitationState state;

    public Invitation(User sender, User receiver, Project project, InvitationState state) {
        this.receiver = receiver;
        this.sender = sender;
        this.project = project;
        this.state = state;
    }

    public static Invitation of(User sender, User receiver, Project project) {
        return new Invitation(sender, receiver, project, InvitationState.WAITING);
    }

    public void accept(ApplicationEventPublisher publisher) {
        state = InvitationState.DONE;

        publisher.publishEvent(new AcceptInvitationEvent(this, receiver, project));
    }

    public void reject() {
        state = InvitationState.REJECT;
    }

    public boolean isNotWaiting() {
        return state != InvitationState.WAITING;
    }

    public boolean isNotSameReceiverId(long receiverId) {
        return !receiver.getId().equals(receiverId);
    }
}
