package com.lcy.ps.project.dto.request.project;

import lombok.*;

import java.time.LocalDate;

@Data
public class CreateProjectDto {
    private Long managerId;

    private String title;
    private String subject;

    private LocalDate startDate;
    private LocalDate deadlineDate;

    @Builder
    public CreateProjectDto(Long managerId, String title, String subject, LocalDate startDate, LocalDate deadlineDate) {
        this.managerId = managerId;
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }
}
