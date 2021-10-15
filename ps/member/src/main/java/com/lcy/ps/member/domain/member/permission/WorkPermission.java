package com.lcy.ps.member.domain.member.permission;

import com.lcy.ps.core.exception.HasNotPermissionException;
import com.lcy.ps.integrate.domain.member.Permission;

import java.util.EnumSet;
import java.util.Set;

public enum WorkPermission implements MemberPermission {
    WORKER(EnumSet.of(Permission.READ, Permission.UPDATE, Permission.DELETE));

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

    @Override
    public String toString() {
        return "W:" + this.name();
    }
}
