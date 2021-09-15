package com.lcy.projectscheduler.api.v1.domain.member.permission;

import com.lcy.projectscheduler.exception.HasNotPermissionException;

import java.util.EnumSet;
import java.util.Set;

public enum SessionPermission implements MemberPermission {
    MEMBER(EnumSet.of(Permission.CREATE, Permission.READ, Permission.UPDATE)),
    MANAGER(EnumSet.of(Permission.CREATE, Permission.READ, Permission.UPDATE, Permission.DELETE));

    private final Set<Permission> permissions;

    SessionPermission(Set<Permission> authorities) {
        this.permissions = authorities;
    }

    @Override
    public void checkPermission(Permission needPermission) {
        if (!permissions.contains(needPermission)) {
            throw new HasNotPermissionException();
        }
    }
}
