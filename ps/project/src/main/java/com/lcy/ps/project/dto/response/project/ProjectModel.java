package com.lcy.ps.project.dto.response.project;


import com.lcy.ps.integrate.dto.MemberModel;
import com.lcy.ps.project.domain.Project;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectModel extends RepresentationModel<ProjectModel> {
    private Long id;
    private String title;
    private String subject;

    private List<MemberModel> members = new ArrayList<>();

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

    public void setMembers(List<MemberModel> members) {
        this.members = members;
    }
}
