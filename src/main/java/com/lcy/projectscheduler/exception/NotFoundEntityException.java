package com.lcy.projectscheduler.exception;

public class NotFoundEntityException extends BusinessException {

    public NotFoundEntityException() {
        super("잘못된 자원의 접근입니다.");
    }
}
