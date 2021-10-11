package com.lcy.ps.core.exception;

public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException() {
        super("유효하지 않은 토큰 입니다.");
    }
}
