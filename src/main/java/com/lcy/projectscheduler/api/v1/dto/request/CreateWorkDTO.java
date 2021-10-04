package com.lcy.projectscheduler.api.v1.dto.request;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateWorkDTO {
    private String title;
    private String contents;
    private Integer priority;

    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;

    private Long worker;

    @Builder
    public CreateWorkDTO(String title, String contents, Integer priority, LocalDateTime startDate, LocalDateTime deadlineDate, Long worker) {
        this.title = title;
        this.contents = contents;
        this.priority = priority;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.worker = worker;
    }
}
