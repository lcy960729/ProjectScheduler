package com.lcy.ps.project.domain.session;

import com.lcy.ps.core.domain.BaseEntity;
import com.lcy.ps.project.domain.Project;
import com.lcy.ps.project.dto.request.session.CreateSessionDTO;
import com.lcy.ps.project.dto.request.session.UpdateSessionDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
