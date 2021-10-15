package com.lcy.ps.project.advisor;

import com.lcy.ps.core.dto.ExceptionModel;
import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.core.exception.NotRegisteredMemberException;
import com.lcy.ps.integrate.domain.member.AuthMemberService;
import com.lcy.ps.integrate.domain.member.Permission;
import com.lcy.ps.project.annotation.MemberID;
import com.lcy.ps.project.annotation.NeedPermission;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
public class MemberAuthAdvisor {

    @Autowired
    private AuthMemberService memberAuthService;

    @Pointcut("@annotation(com.lcy.ps.project.annotation.NeedPermission)")
    public void memberAuth() {
    }

    private int getMemberIdIndex(Method method) {
        for (Annotation[] annotations : method.getParameterAnnotations()) {
            for (int i = 0; i < annotations.length; i++) {
                if (annotations[i] instanceof MemberID) {
                    return i;
                }
            }
        }

        throw new RuntimeException();
    }

    private Permission getNeedPermission(MethodSignature methodSignature) {
        for (Annotation declaredAnnotation : methodSignature.getMethod().getDeclaredAnnotations()) {
            if (declaredAnnotation instanceof NeedPermission) {
                return ((NeedPermission) declaredAnnotation).value();
            }
        }

        throw new RuntimeException();
    }

    @Around(value = "memberAuth()")
    public Object auth(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature methodSignature = ((MethodSignature) pjp.getSignature());

        try {
            int id = getMemberIdIndex(methodSignature.getMethod());
            Long memberId = ((Long) pjp.getArgs()[id]);

            Permission needPermission = getNeedPermission(methodSignature);

            if (Objects.isNull(memberId)) {
                throw new NotFoundEntityException();
            }

            memberAuthService.auth(memberId, needPermission);

            return pjp.proceed(pjp.getArgs());

        } catch (NotRegisteredMemberException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ExceptionModel.of(e));
        }
    }
}
