package com.lcy.ps.member.domain.member.projectMember;

import com.lcy.ps.member.domain.member.Member;
import com.lcy.ps.member.domain.member.permission.MemberPermission;
import com.lcy.ps.member.domain.member.permission.ProjectPermission;
import com.lcy.ps.member.domain.member.state.MemberState;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@DiscriminatorValue("P")
public class ProjectMember extends Member {

    @Column(name = "project_id")
    private Long projectId;

    private ProjectMember(long userId, long projectId, MemberState memberState, MemberPermission memberPermission) {
        super(userId, memberState, memberPermission);
        this.projectId = projectId;
    }

    public static ProjectMember registerSuperManager(long userId, long projectId) {
        return new ProjectMember(userId, projectId, MemberState.JOINED, ProjectPermission.SUPER_MANAGER);
    }

    public static ProjectMember registerMember(long userId, long projectId) {
        return new ProjectMember(userId, projectId, MemberState.JOINED, ProjectPermission.MEMBER);
    }

}
