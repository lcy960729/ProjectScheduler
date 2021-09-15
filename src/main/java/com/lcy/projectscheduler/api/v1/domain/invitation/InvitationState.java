package com.lcy.projectscheduler.api.v1.domain.invitation;

import com.lcy.projectscheduler.exception.AlreadyProcessedInvitationException;
import com.lcy.projectscheduler.exception.HasNotPermissionException;
import org.springframework.context.ApplicationEventPublisher;

public enum InvitationState {
    WAITING {
        @Override
        void accept(Invitation invitation, ApplicationEventPublisher publisher) {
            invitation.setState(InvitationState.DONE);
            publisher.publishEvent(new AcceptInvitationEvent(this, invitation.getReceiver(), invitation.getProject()));
        }

        @Override
        void reject(Invitation invitation) {
            invitation.setState(InvitationState.REJECT);
        }
    },

    DONE {
        @Override
        void accept(Invitation invitation, ApplicationEventPublisher publisher) {
            throw new AlreadyProcessedInvitationException();
        }

        @Override
        void reject(Invitation invitation) {
            throw new AlreadyProcessedInvitationException();
        }
    },

    REJECT {
        @Override
        void accept(Invitation invitation, ApplicationEventPublisher publisher) {
            throw new AlreadyProcessedInvitationException();
        }

        @Override
        void reject(Invitation invitation) {
            throw new AlreadyProcessedInvitationException();
        }
    };

    abstract void accept(Invitation invitation, ApplicationEventPublisher publisher);

    abstract void reject(Invitation invitation);
}
