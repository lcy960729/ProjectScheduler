package com.lcy.ps.project.annotation;



import com.lcy.ps.integrate.domain.member.Permission;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NeedPermission {
    Permission value();
}
