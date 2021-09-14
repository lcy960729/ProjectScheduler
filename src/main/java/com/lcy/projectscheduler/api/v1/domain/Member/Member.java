package com.lcy.projectscheduler.api.v1.domain.Member;

import com.lcy.projectscheduler.api.v1.domain.Member.permission.Permission;

public interface Member {
    void auth(Permission needPermission);
}
