package com.lcy.projectscheduler.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String cause) {
        super(cause);
    }
}
