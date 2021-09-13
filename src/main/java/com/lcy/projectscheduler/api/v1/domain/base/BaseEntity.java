package com.lcy.projectscheduler.api.v1.domain.base;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BaseEntity {
    protected Long id;
    protected LocalDateTime created;
    protected LocalDateTime updated;
}
