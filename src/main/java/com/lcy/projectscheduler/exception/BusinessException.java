package com.lcy.projectscheduler.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String cause) {
        super(cause);
    }
}
