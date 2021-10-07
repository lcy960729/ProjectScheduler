package com.lcy.projectscheduler.exception;


public class HasNotPermissionException extends BusinessException {

    public HasNotPermissionException() {
        super("해당 요청에 권한이 없습니다.");
    }
}
