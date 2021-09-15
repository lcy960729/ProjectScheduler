package com.lcy.projectscheduler.api.v1.domain.project.session.work;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Work extends BaseEntity {
    private String title;
    private String content;

    private Integer priority;

    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;

    private WorkState state;

    private Worker worker;
    private List<Worker> coworker;
}
