package com.lcy.ps.core.exceptionHandler;

import com.lcy.ps.core.exception.BusinessException;
import com.lcy.ps.core.dto.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionModel> businessExceptionHandler(BusinessException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionModel.of(e));
    }
}
