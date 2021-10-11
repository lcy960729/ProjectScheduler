package com.lcy.ps.core.exception;

public class NotRegisteredMemberException extends BusinessException {

    public NotRegisteredMemberException() {
        super("요청한 동작의 그룹에 참여되지 않았습니다.");
    }
}
