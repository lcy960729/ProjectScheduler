package com.lcy.ps.core.domain.member;

import com.lcy.ps.core.domain.member.permission.Permission;

public interface Member {
    void checkRegisteredAndPermission(Permission needPermission);

    String getEmail();
}
