package com.lcy.ps.project;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(ProjectModuleConfiguration.class)
public @interface EnableProjectModule {
}
