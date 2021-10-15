package com.lcy.ps.member.domain.member.projectMember.sessionMember.worker;

import com.lcy.ps.member.domain.member.Member;
import com.lcy.ps.member.domain.member.permission.WorkPermission;
import com.lcy.ps.member.domain.member.state.MemberState;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.SessionMember;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@DiscriminatorValue("W")
public class Worker extends Member {
    @Column(name = "work_id")
    private Long workId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private SessionMember sessionMember;

    private Worker(long userId, long workId, MemberState state, WorkPermission workPermission, SessionMember sessionMember) {
        super(userId, state, workPermission);
        this.workId = workId;
        this.sessionMember = sessionMember;
    }

    public static Worker registerWorker(long workId, SessionMember sessionMember) {
        return new Worker(sessionMember.getUserId(), workId, MemberState.JOINED, WorkPermission.WORKER, sessionMember);
    }
}
