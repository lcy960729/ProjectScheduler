package com.lcy.projectscheduler.api.v1.domain;

public class Work extends BaseEntity {
    private User worker;
    private WorkState state;
    private String title;
    private String content;
}
