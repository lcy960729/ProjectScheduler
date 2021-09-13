package com.lcy.projectscheduler.api.v1.domain;

import java.util.ArrayList;
import java.util.List;

public class Project extends BaseEntity{
    private String name;
    private List<User> participants = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();


}
