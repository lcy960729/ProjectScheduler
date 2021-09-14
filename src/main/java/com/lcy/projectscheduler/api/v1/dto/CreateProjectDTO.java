package com.lcy.projectscheduler.api.v1.dto;

import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateProjectDTO {
    private String title;
    private String subject;

    private LocalDate startDate;
    private LocalDate deadlineDate;

    private Long Manager;

    @Builder
    public CreateProjectDTO(String title, String subject, LocalDate startDate, LocalDate deadlineDate, Long manager) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        Manager = manager;
    }
}
