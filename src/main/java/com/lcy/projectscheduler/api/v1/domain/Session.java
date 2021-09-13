package com.lcy.projectscheduler.api.v1.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Session extends BaseEntity {
    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;

    private List<User> participants;



}
