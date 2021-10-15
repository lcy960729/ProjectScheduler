package com.lcy.ps.member.domain.member.projectMember.sessionMember;

import com.lcy.ps.member.domain.member.Member;
import com.lcy.ps.member.domain.member.permission.SessionPermission;
import com.lcy.ps.member.domain.member.state.MemberState;
import com.lcy.ps.member.domain.member.projectMember.ProjectMember;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@DiscriminatorValue("S")
public class SessionMember extends Member {

    @Column(name = "session_id")
    private Long sessionId;

    @ManyToOne
    @JoinColumn
    private ProjectMember projectMember;

    private SessionMember(long userId, long sessionId, MemberState state, SessionPermission sessionPermission, ProjectMember projectMember) {
        super(userId, state, sessionPermission);

        this.sessionId = sessionId;
        this.projectMember = projectMember;
    }

    public static SessionMember registerManager(long sessionId, ProjectMember projectMember) {
        return new SessionMember(projectMember.getUserId(), sessionId, MemberState.JOINED, SessionPermission.MANAGER, projectMember);
    }

    public static SessionMember registerMember(long sessionId, ProjectMember projectMember) {
        return new SessionMember(projectMember.getUserId(), sessionId, MemberState.JOINED, SessionPermission.MEMBER, projectMember);
    }


}
