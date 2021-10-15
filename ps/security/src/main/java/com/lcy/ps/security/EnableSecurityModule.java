package com.lcy.ps.security;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(SecurityModuleConfiguration.class)
public @interface EnableSecurityModule {
}
