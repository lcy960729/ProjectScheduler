package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.project.Project;

public enum SessionAuthority {
    MEMBER(false),
    MANAGER(true);

    private final boolean canChange;

    SessionAuthority(boolean canChange) {
        this.canChange = canChange;
    }

    public boolean auth(Project project) {
        return canChange;
    }
}
