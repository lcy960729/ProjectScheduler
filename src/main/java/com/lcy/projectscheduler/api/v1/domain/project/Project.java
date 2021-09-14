package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.temp.session.Session;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.dto.CreateProjectDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.core.ApplicationPushBuilder;
import org.springframework.context.ApplicationEventPublisher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
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

    @OneToMany(mappedBy = "project")
    private Set<ProjectMember> participants = new HashSet<>();

    @Transient
    private Set<Session> sessions = new HashSet<>();

    private Project(String title, String subject, LocalDate startDate, LocalDate deadlineDate) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }

    public static Project create(CreateProjectDTO dto) {
        return new Project(dto.getTitle(), dto.getSubject(), dto.getStartDate(), dto.getDeadlineDate());
    }

    public void addMember(ProjectMember projectMember) {
        participants.add(projectMember);
    }
}
