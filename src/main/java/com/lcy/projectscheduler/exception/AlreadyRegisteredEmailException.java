package com.lcy.projectscheduler.exception;

public class AlreadyRegisteredEmailException extends BusinessException {

    public AlreadyRegisteredEmailException() {
        super("이미 등록된 이메일 입니다");
    }
}
