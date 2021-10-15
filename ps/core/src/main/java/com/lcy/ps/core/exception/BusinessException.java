package com.lcy.ps.core.exception;

public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String cause) {
        super(cause);
    }
}
