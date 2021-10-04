package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import com.lcy.projectscheduler.api.v1.dto.request.project.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.dto.request.project.UpdateProjectDTO;
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

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ProjectMember> projectMembers = new HashSet<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Session> sessions = new HashSet<>();

    private Project(String title, String subject, LocalDate startDate, LocalDate deadlineDate) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }

    public static Project of(CreateProjectDTO dto) {
        return new Project(dto.getTitle(), dto.getSubject(), dto.getStartDate(), dto.getDeadlineDate());
    }

    public void addMember(ProjectMember projectMember) {
        projectMembers.add(projectMember);
    }

    public void update(UpdateProjectDTO dto) {
        this.title = dto.getTitle();
        this.subject = dto.getSubject();
        this.startDate = dto.getStartDate();
        this.deadlineDate = dto.getDeadlineDate();
    }

}
