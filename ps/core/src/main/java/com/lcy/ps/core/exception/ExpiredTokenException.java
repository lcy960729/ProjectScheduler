package com.lcy.ps.core.exception;

public class ExpiredTokenException extends AuthenticationException {

    public ExpiredTokenException() {
        super("만료된 토큰 입니다.");
    }
}
