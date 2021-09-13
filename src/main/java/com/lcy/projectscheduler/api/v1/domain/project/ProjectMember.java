package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.user.User;

public class ProjectMember extends BaseEntity {
    private User user;
    private Project project;

    private ProjectAuthority projectAuthority;

    public boolean auth(Project project) {
        return projectAuthority.auth(project);
    }
}
