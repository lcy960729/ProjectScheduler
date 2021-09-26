package com.lcy.projectscheduler.api.v1.domain.member.permission;

import com.lcy.projectscheduler.exception.HasNotPermissionException;

import java.util.EnumSet;
import java.util.Set;

public enum WorkPermission implements MemberPermission {
    WORKER(EnumSet.of(Permission.READ, Permission.UPDATE, Permission.DELETE)),
    COWORKER(EnumSet.of(Permission.READ, Permission.UPDATE));

    private final Set<Permission> permissions;

    WorkPermission(Set<Permission> authorities) {
        this.permissions = authorities;
    }

    @Override
    public void checkPermission(Permission needPermission) {
        if (!permissions.contains(needPermission)) {
            throw new HasNotPermissionException();
        }
    }
}
