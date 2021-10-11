package com.lcy.ps.core.domain.project.session;

import com.lcy.ps.core.domain.base.BaseEntity;
import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.dto.request.session.CreateSessionDTO;
import com.lcy.ps.core.dto.request.session.UpdateSessionDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "session")
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

    @OneToMany(mappedBy = "session", cascade = CascadeType.REMOVE)
    private Set<SessionMember> sessionMembers = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private Project project;

    @Column
    @Enumerated(EnumType.STRING)
    private SessionState state;

    private Session(String title, SessionState state, String subject, LocalDateTime startDate, LocalDateTime deadlineDate, Project project) {
        this.title = title;
        this.state = state;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.project = project;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
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

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void update(UpdateSessionDTO dto) {
        this.title = dto.getTitle();
        this.subject = dto.getSubject();
        this.startDate = dto.getStartDate();
        this.deadlineDate = dto.getDeadlineDate();
        this.deadlineDate = dto.getDeadlineDate();
    }
}
