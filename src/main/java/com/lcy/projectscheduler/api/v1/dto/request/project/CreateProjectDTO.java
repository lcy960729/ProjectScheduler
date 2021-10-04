package com.lcy.projectscheduler.api.v1.dto.request.project;

import lombok.*;

import java.time.LocalDate;

@Data
public class CreateProjectDTO {
    private String title;
    private String subject;

    private LocalDate startDate;
    private LocalDate deadlineDate;

    private Long manager;

    @Builder
    public CreateProjectDTO(String title, String subject, LocalDate startDate, LocalDate deadlineDate, Long manager) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.manager = manager;
    }
}
