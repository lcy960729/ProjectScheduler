package com.lcy.ps.core.domain.member.permission;

import com.lcy.ps.core.exception.HasNotPermissionException;

import java.util.EnumSet;
import java.util.Set;

public enum SessionPermission implements MemberPermission {
    MEMBER(EnumSet.of(Permission.READ, Permission.UPDATE)),
    MANAGER(EnumSet.of(Permission.CREATE, Permission.READ, Permission.UPDATE, Permission.DELETE, Permission.INVITE));

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
