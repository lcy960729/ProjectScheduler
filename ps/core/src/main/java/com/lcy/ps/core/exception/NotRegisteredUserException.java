package com.lcy.ps.core.exception;

public class NotRegisteredUserException extends NotFoundEntityException {

    public NotRegisteredUserException() {
        super("가입 되지 않은 회원 입니다.");
    }
}
