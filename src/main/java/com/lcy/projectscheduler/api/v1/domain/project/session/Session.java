package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.dto.CreateSessionDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Session extends BaseEntity {
    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private SessionState state;

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

    private Session(String title, SessionState state, String subject, LocalDateTime startDate, LocalDateTime deadlineDate, Project project) {
        this.title = title;
        this.state = state;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.project = project;
    }

    public static Session of(Project project, CreateSessionDTO dto) {
        return new Session(dto.getTitle(),
                SessionState.NOT_STARTED,
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

    public boolean isNotJoined(long userId) {
        return !sessionMembers.contains(userId);
    }

    public Project getProject() {
        return project;
    }
}
