package com.lcy.ps.member;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(MemberModuleConfiguration.class)
public @interface EnableMemberModule {
}
