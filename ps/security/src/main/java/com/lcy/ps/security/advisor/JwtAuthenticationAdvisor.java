package com.lcy.ps.security.advisor;


import com.lcy.ps.core.dto.ExceptionModel;
import com.lcy.ps.core.exception.InvalidTokenException;
import com.lcy.ps.integrate.domain.user.UserAuthService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class JwtAuthenticationAdvisor {

    @Autowired
    private UserAuthService userAuthService;

    @Pointcut("execution(* com.lcy.ps.*.controller.*.*(..))")
    public void authPoint() {
    }

    @Around("authPoint()")
    public Object auth(ProceedingJoinPoint pjp) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            userAuthService.auth(token);

            return pjp.proceed();

        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ExceptionModel.of(e));
        }
    }
}
