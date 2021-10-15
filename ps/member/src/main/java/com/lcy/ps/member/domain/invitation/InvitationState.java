package com.lcy.ps.member.domain.invitation;

import com.lcy.ps.core.exception.AlreadyProcessedInvitationException;
import org.springframework.context.ApplicationEventPublisher;

public enum InvitationState {
    WAITING {
        @Override
        public void accept(Invitation invitation, ApplicationEventPublisher publisher) {
            invitation.setState(InvitationState.DONE);
            publisher.publishEvent(new AcceptInvitationEvent(this, invitation.getReceiver(), invitation.getProject()));
        }

        @Override
        public void reject(Invitation invitation) {
            invitation.setState(InvitationState.REJECT);
        }
    },

    DONE {
        @Override
        public void accept(Invitation invitation, ApplicationEventPublisher publisher) {
            throw new AlreadyProcessedInvitationException();
        }

        @Override
        public void reject(Invitation invitation) {
            throw new AlreadyProcessedInvitationException();
        }
    },

    REJECT {
        @Override
        public void accept(Invitation invitation, ApplicationEventPublisher publisher) {
            throw new AlreadyProcessedInvitationException();
        }

        @Override
        public void reject(Invitation invitation) {
            throw new AlreadyProcessedInvitationException();
        }
    };

    public abstract void accept(Invitation invitation, ApplicationEventPublisher publisher);

    public abstract void reject(Invitation invitation);
}
