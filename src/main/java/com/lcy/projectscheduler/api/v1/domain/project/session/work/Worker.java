package com.lcy.projectscheduler.api.v1.domain.project.session.work;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.member.Member;
import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;
import com.lcy.projectscheduler.api.v1.domain.member.permission.WorkPermission;
import com.lcy.projectscheduler.api.v1.domain.member.state.MemberState;
import com.lcy.projectscheduler.api.v1.domain.user.User;

import javax.persistence.*;

@Entity
public class Worker extends BaseEntity implements Member {
    @ManyToOne
    @JoinColumn
    private Work work;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberState state;

    @Column
    @Enumerated(EnumType.STRING)
    private WorkPermission workPermission;

    public Work getWork() {
        return work;
    }

    public User getUser() {
        return user;
    }

    public MemberState getState() {
        return state;
    }

    public WorkPermission getWorkPermission() {
        return workPermission;
    }

    private Worker(Work work, User user, MemberState state, WorkPermission workPermission) {
        this.work = work;
        this.user = user;
        this.state = state;
        this.workPermission = workPermission;
    }

    public static Worker registerWorker(User user, Work work) {
        return new Worker(work, user, MemberState.JOINED, WorkPermission.WORKER);
    }

    public static Worker registerCoworker(User user, Work work) {
        return new Worker(work, user, MemberState.JOINED, WorkPermission.COWORKER);
    }

    @Override
    public void checkRegisteredAndPermission(Permission needPermission) {

    }

    @Override
    public String getEmail() {
        return getUser().getEmail();
    }
}
