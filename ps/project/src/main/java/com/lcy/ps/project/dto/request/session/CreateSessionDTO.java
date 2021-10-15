package com.lcy.ps.project.dto.request.session;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateSessionDTO {
    private Long managerId;

    private String title;
    private String subject;

    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;

    @Builder
    public CreateSessionDTO(String title, String subject, LocalDateTime startDate, LocalDateTime deadlineDate) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }
}
