package com.lcy.ps.core;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(CoreModuleConfiguration.class)
public @interface EnableCoreModule {
}
