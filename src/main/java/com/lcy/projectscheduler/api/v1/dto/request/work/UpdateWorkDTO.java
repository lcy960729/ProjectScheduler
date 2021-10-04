package com.lcy.projectscheduler.api.v1.dto.request.work;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateWorkDTO {
    private String title;
    private String contents;
    private Integer priority;

    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;
}
