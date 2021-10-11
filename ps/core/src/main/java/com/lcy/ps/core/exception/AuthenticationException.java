package com.lcy.ps.core.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String cause) {
        super(cause);
    }
}
