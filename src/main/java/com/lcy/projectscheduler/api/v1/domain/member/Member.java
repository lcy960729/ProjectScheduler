package com.lcy.projectscheduler.api.v1.domain.member;

import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;

public interface Member {
    void auth(Permission needPermission);
}
