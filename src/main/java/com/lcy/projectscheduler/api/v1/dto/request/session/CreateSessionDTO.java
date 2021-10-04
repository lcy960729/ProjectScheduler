package com.lcy.projectscheduler.api.v1.dto.request.session;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateSessionDTO {
    private String title;
    private String subject;

    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;

    private Long Manager;

    @Builder
    public CreateSessionDTO(String title, String subject, LocalDateTime startDate, LocalDateTime deadlineDate, Long manager) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        Manager = manager;
    }
}
