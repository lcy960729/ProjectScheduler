package com.lcy.projectscheduler.api.v1.domain.Member.state;

import com.lcy.projectscheduler.exception.NotRegisteredMemberException;

public enum MemberState {
    JOINED, LEFT;

    public void checkRegistered() {
        if (this == LEFT)
            throw new NotRegisteredMemberException();
    }
}
