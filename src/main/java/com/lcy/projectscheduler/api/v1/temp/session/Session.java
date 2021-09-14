package com.lcy.projectscheduler.api.v1.temp.session;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.dto.CreateSessionDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    public static Session create(Project project, CreateSessionDTO dto) {
        return new Session(
                dto.getTitle(),
                dto.getSubject(),
                dto.getStartDate(),
                dto.getDeadlineDate(),
                project);
    }
}
