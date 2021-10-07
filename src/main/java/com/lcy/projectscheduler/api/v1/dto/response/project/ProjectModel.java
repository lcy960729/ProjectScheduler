package com.lcy.projectscheduler.api.v1.dto.response.project;

import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.security.dto.UserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectModel extends RepresentationModel<ProjectModel> {
    private Long id;
    private String title;
    private String subject;

    private Set<UserModel> projectMembers = new HashSet<>();

    private LocalDate startDate;
    private LocalDate deadlineDate;


    private ProjectModel(Long id, String title, String subject, Set<ProjectMember> projectMembers, LocalDate startDate, LocalDate deadlineDate) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.projectMembers = projectMembers.stream()
                .map(UserModel::of)
                .collect(Collectors.toSet());
    }

    public static ProjectModel of(Project project) {
        return new ProjectModel(project.getId(), project.getTitle(), project.getSubject(), project.getProjectMembers(), project.getStartDate(), project.getDeadlineDate());
    }
}
