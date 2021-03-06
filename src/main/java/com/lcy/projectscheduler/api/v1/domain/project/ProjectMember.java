package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;
import com.lcy.projectscheduler.api.v1.domain.member.permission.ProjectPermission;
import com.lcy.projectscheduler.api.v1.domain.member.Member;
import com.lcy.projectscheduler.api.v1.domain.member.state.MemberState;
import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.security.domain.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ProjectMember extends BaseEntity implements Member {

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Project project;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberState state;

    @Column
    @Enumerated(EnumType.STRING)
    private ProjectPermission projectPermission;

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    private ProjectMember(User user, Project project, MemberState memberState, ProjectPermission projectAuthority) {
        this.user = user;
        this.project = project;
        this.state = memberState;
        this.projectPermission = projectAuthority;
    }

    public static ProjectMember registerSuperManager(User user, Project project) {
        return new ProjectMember(user, project, MemberState.JOINED, ProjectPermission.SUPER_MANAGER);
    }

    public static ProjectMember registerMember(User user, Project project) {
        return new ProjectMember(user, project, MemberState.JOINED, ProjectPermission.MEMBER);
    }

    @Override
    public void checkRegisteredAndPermission(Permission needPermission) {
        state.checkRegistered();
        projectPermission.checkPermission(needPermission);
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    public MemberState getState() {
        return state;
    }

    public ProjectPermission getProjectPermission() {
        return projectPermission;
    }
}
