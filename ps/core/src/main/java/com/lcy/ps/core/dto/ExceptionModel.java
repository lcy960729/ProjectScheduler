package com.lcy.ps.core.dto;

import com.lcy.ps.core.exception.AuthenticationException;
import com.lcy.ps.core.exception.BusinessException;
import lombok.Data;

@Data
public class ExceptionModel {
    private final int status;
    private final String msg;

    private ExceptionModel(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static ExceptionModel of(BusinessException e) {
        return new ExceptionModel(404, e.getMessage());
    }

    public static ExceptionModel of(AuthenticationException e) {
        return new ExceptionModel(404, e.getMessage());
    }
}