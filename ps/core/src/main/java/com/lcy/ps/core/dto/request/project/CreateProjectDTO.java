package com.lcy.ps.core.dto.request.project;

import lombok.*;

import java.time.LocalDate;

@Data
public class CreateProjectDTO {
    private String title;
    private String subject;

    private LocalDate startDate;
    private LocalDate deadlineDate;

    @Builder
    public CreateProjectDTO(String title, String subject, LocalDate startDate, LocalDate deadlineDate) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }
}
