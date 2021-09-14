package com.lcy.projectscheduler.api.v1.domain.Member.permission;

import com.lcy.projectscheduler.exception.HasNotPermissionException;

import java.util.*;

public enum ProjectPermission implements MemberPermission {
    MEMBER(EnumSet.of(Permission.CREATE, Permission.READ)),
    MANAGER(EnumSet.of(Permission.CREATE, Permission.READ, Permission.UPDATE, Permission.DELETE, Permission.INVITE)),
    SUPER_MANAGER(EnumSet.of(Permission.CREATE, Permission.READ, Permission.UPDATE, Permission.DELETE, Permission.INVITE, Permission.GRANT));

    private final Set<Permission> permissions;

    ProjectPermission(Set<Permission> authorities) {
        this.permissions = authorities;
    }

    @Override
    public void checkPermission(Permission needPermission) {
        if (!permissions.contains(needPermission)) {
            throw new HasNotPermissionException();
        }
    }
}
