package com.lcy.ps.project.dto.request.work;

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
