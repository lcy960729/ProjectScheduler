package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
public class Session extends BaseEntity {
    private String title;
    private String subject;

    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;

    private List<SessionMember> sessionMembers;

    public Session(String title, String subject, LocalDateTime startDate, LocalDateTime deadlineDate, List<SessionMember> sessionMembers) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.sessionMembers = sessionMembers;
    }
}
