package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.dto.CreateSessionDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Session extends BaseEntity {
    @Column
    private String title;
    @Column
    private String subject;

    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime deadlineDate;

    @OneToMany(mappedBy = "session")
    private Set<SessionMember> sessionMembers = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private Project project;

    private Session(String title, String subject, LocalDateTime startDate, LocalDateTime deadlineDate, Project project) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.project = project;
    }

    public static Session of(Project project, CreateSessionDTO dto) {
        return new Session(dto.getTitle(),
                dto.getSubject(),
                dto.getStartDate(),
                dto.getDeadlineDate(),
                project);
    }

    public void addMember(SessionMember sessionMember) {
        sessionMembers.add(sessionMember);
    }

    public Set<SessionMember> getSessionMembers() {
        return sessionMembers;
    }
}
