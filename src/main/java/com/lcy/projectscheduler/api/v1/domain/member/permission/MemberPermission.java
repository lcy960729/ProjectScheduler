package com.lcy.projectscheduler.api.v1.domain.member.permission;

public interface MemberPermission {
    void checkPermission(Permission needPermission);
}
