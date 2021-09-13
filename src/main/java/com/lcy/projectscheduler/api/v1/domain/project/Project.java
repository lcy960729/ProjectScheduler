package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.project.session.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Project extends BaseEntity {
    private String title;
    private String subject;

    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;

    private List<ProjectMember> participants = new ArrayList<>();

    private List<Session> sessions = new ArrayList<>();

    public Session createSession() {
        return new Session();
    }


}
