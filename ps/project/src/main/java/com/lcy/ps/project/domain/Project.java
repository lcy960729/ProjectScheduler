package com.lcy.ps.project.domain;

import com.lcy.ps.core.domain.BaseEntity;
import com.lcy.ps.project.domain.session.Session;
import com.lcy.ps.project.dto.request.project.CreateProjectDto;
import com.lcy.ps.project.dto.request.project.UpdateProjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity(name = "project")
@NoArgsConstructor
public class Project extends BaseEntity {
    @Column
    private String title;

    @Column
    private String subject;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate deadlineDate;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<>();

    private Project(String title, String subject, LocalDate startDate, LocalDate deadlineDate) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }

    public static Project of(CreateProjectDto dto) {
        return new Project(dto.getTitle(), dto.getSubject(), dto.getStartDate(), dto.getDeadlineDate());
    }

    public void update(UpdateProjectDto dto) {
        this.title = dto.getTitle();
        this.subject = dto.getSubject();
        this.startDate = dto.getStartDate();
        this.deadlineDate = dto.getDeadlineDate();
    }
}
