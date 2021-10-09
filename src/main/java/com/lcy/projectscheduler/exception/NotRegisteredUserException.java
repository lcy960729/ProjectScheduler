package com.lcy.projectscheduler.exception;

public class NotRegisteredUserException extends NotFoundEntityException {

    public NotRegisteredUserException() {
        super("요청한 동작의 그룹에 참여되지 않았습니다.");
    }
}
