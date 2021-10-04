package com.lcy.projectscheduler.api.v1.dto.request.project;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateProjectDTO {
    private String title;
    private String subject;

    private LocalDate startDate;
    private LocalDate deadlineDate;

    @Builder
    public UpdateProjectDTO(String title, String subject, LocalDate startDate, LocalDate deadlineDate) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }
}
