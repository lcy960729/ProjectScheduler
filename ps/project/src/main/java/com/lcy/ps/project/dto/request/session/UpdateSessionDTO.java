package com.lcy.ps.project.dto.request.session;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateSessionDTO {
    private String title;
    private String subject;

    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;

    private Long manager;

    @Builder
    public UpdateSessionDTO(String title, String subject, LocalDateTime startDate, LocalDateTime deadlineDate, Long manager) {
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.manager = manager;
    }
}
