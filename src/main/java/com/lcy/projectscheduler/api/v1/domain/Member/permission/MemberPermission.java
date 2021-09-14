package com.lcy.projectscheduler.api.v1.domain.Member.permission;

public interface MemberPermission {
    void checkPermission(Permission needPermission);
}
