package com.lcy.ps.main.api.v1.aop;


import com.lcy.ps.core.enums.SecurityHeaders;
import com.lcy.ps.core.exception.InvalidTokenException;
import com.lcy.ps.core.exceptionHandler.dto.ExceptionModel;
import com.lcy.ps.main.api.v1.domain.user.AuthService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class JwtAuthentication {

    @Autowired
    private AuthService authService;

    @Around("execution(* com.lcy.ps.main.api.v1.controller.*.*(..))")
    public Object auth(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String token = request.getHeader(SecurityHeaders.AUTHORIZATION.getName());

        try {
            long userId = authService.auth(token);
            Object[] args = joinPoint.getArgs();
            args[0] = userId;

            return joinPoint.proceed(args);
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ExceptionModel.of(e));
        }
    }
}
