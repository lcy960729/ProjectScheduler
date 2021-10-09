package com.lcy.projectscheduler.api.v1.domain.invitation;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.authorization.domain.User;
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
    @Enumerated(value = EnumType.STRING)
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
        state.accept(this, publisher);
    }

    public void reject() {
        state.reject(this);
    }

    public boolean isNotSameReceiverId(long receiverId) {
        return !receiver.getId().equals(receiverId);
    }

    public InvitationState getState() {
        return state;
    }

    protected void setState(InvitationState state) {
        this.state = state;
    }

    protected User getReceiver() {
        return receiver;
    }

    protected Project getProject() {
        return project;
    }
}
