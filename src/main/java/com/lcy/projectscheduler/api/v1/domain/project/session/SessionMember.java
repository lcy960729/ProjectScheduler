package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.user.User;

public class SessionMember {
    private User user;
    private Session session;

    private SessionAuthority sessionAuthority;
}
