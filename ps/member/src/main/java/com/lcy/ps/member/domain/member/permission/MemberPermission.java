package com.lcy.ps.member.domain.member.permission;

import com.lcy.ps.integrate.domain.member.Permission;

public interface MemberPermission {
    void checkPermission(Permission needPermission);
}
