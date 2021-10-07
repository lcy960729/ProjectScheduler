package com.lcy.projectscheduler.security.aop;


import com.lcy.projectscheduler.exception.InvalidTokenException;
import com.lcy.projectscheduler.exceptionHandler.dto.ExceptionModel;
import com.lcy.projectscheduler.security.JwtTokenProvider;
import com.lcy.projectscheduler.security.SecurityHeaders;
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
    private JwtTokenProvider jwtTokenProvider;

    @Around("execution(* com.lcy.projectscheduler.api.v1.controller.*.*(..))")
    public Object auth(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String token = request.getHeader(SecurityHeaders.AUTHORIZATION.getName());

        try {
            long userId = jwtTokenProvider.validateTokenAndReturnUserId(token);
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
