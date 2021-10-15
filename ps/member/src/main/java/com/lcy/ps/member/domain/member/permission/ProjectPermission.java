package com.lcy.ps.member.domain.member.permission;

import com.lcy.ps.core.exception.HasNotPermissionException;
import com.lcy.ps.integrate.domain.member.Permission;

import java.util.*;

public enum ProjectPermission implements MemberPermission {
    MEMBER(EnumSet.of(Permission.READ, Permission.CREATE)),
    MANAGER(EnumSet.of(Permission.READ, Permission.CREATE, Permission.UPDATE, Permission.DELETE, Permission.INVITE)),
    SUPER_MANAGER(EnumSet.of(Permission.READ, Permission.CREATE, Permission.UPDATE, Permission.DELETE, Permission.INVITE, Permission.GRANT));

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

    @Override
    public String toString() {
        return "P:" + this.name();
    }
}
