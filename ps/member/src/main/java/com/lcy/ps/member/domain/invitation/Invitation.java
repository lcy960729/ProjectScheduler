package com.lcy.ps.member.domain.invitation;

import com.lcy.ps.core.domain.BaseEntity;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
public class Invitation extends BaseEntity {

    @Column(name = "receiver_user_id")
    private Long receiver;

    @Column(name = "receiver_sender_id")
    private Long sender;

    @Column(name = "project_id")
    private Long project;

    public Long getReceiver() {
        return receiver;
    }

    public Long getSender() {
        return sender;
    }

    public Long getProject() {
        return project;
    }

    @Column
    @Enumerated(value = EnumType.STRING)
    private InvitationState state;

    public Invitation(long sender, long receiver, long project, InvitationState state) {
        this.receiver = receiver;
        this.sender = sender;
        this.project = project;
        this.state = state;
    }

    public static Invitation create(long sender, long receiver, long project) {
        return new Invitation(sender, receiver, project, InvitationState.WAITING);
    }

    public void accept(ApplicationEventPublisher publisher) {
        state.accept(this, publisher);
    }

    public void reject() {
        state.reject(this);
    }

    public boolean isNotSameReceiverId(long receiverId) {
        return !receiver.equals(receiverId);
    }

    public InvitationState getState() {
        return state;
    }

    protected void setState(InvitationState state) {
        this.state = state;
    }
}
