package com.lcy.projectscheduler.api.v1.dto.response.project;

import com.lcy.projectscheduler.api.v1.domain.project.Project;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
public class ProjectModel extends RepresentationModel<ProjectModel> {
    private Long id;
    private String title;
    private String subject;

    private LocalDate startDate;
    private LocalDate deadlineDate;

    private ProjectModel(Long id, String title, String subject, LocalDate startDate, LocalDate deadlineDate) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }

    public static ProjectModel of(Project project) {
        return new ProjectModel(project.getId(), project.getTitle(), project.getSubject(), project.getStartDate(), project.getDeadlineDate());
    }
}
