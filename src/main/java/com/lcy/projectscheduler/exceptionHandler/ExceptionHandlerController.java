package com.lcy.projectscheduler.exceptionHandler;

import com.lcy.projectscheduler.exception.AuthenticationException;
import com.lcy.projectscheduler.exception.BusinessException;
import com.lcy.projectscheduler.exception.InvalidTokenException;
import com.lcy.projectscheduler.exception.NotFoundEntityException;
import com.lcy.projectscheduler.exceptionHandler.dto.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionModel> businessExceptionHandler(BusinessException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionModel.of(e));
    }
}
