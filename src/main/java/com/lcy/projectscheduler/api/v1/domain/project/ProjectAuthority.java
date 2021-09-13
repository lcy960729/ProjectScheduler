package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import com.lcy.projectscheduler.api.v1.domain.project.session.work.Work;

public enum ProjectAuthority {
    MEMBER(false),
    MANAGER(true);

    private final boolean canChange;

    ProjectAuthority(boolean canChange) {
        this.canChange = canChange;
    }

    public boolean auth(Project project) {
        return canChange;
    }
}
