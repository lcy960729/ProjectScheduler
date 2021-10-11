package com.lcy.ps.core.exception;

public class SignInException extends BusinessException {

    public SignInException() {
        super("로그인 정보가 잘못되었습니다.");
    }
}
