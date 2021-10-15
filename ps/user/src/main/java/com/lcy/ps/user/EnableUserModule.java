package com.lcy.ps.user;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(UserModuleConfiguration.class)
public @interface EnableUserModule {
}
