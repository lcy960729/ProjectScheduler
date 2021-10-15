package com.lcy.ps.member.domain.member.state;

import com.lcy.ps.core.exception.NotRegisteredMemberException;

public enum MemberState {
    JOINED, LEFT;

    public void checkRegistered() {
        if (this == LEFT)
            throw new NotRegisteredMemberException();
    }
}
