package com.lcy.ps.core.domain.project.session;

import com.lcy.ps.core.domain.base.BaseEntity;
import com.lcy.ps.core.domain.member.Member;
import com.lcy.ps.core.domain.member.permission.Permission;
import com.lcy.ps.core.domain.member.permission.SessionPermission;
import com.lcy.ps.core.domain.member.state.MemberState;
import com.lcy.ps.core.domain.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
public class SessionMember extends BaseEntity implements Member {
    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Session session;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberState state;

    @Column
    @Enumerated(EnumType.STRING)
    private SessionPermission sessionPermission;

    @Override
    public void checkRegisteredAndPermission(Permission needPermission) {
        state.checkRegistered();
        sessionPermission.checkPermission(needPermission);
    }

    private SessionMember(User user, Session session, MemberState state, SessionPermission sessionPermission) {
        this.user = user;
        this.session = session;
        this.state = state;
        this.sessionPermission = sessionPermission;
    }

    public static SessionMember registerManager(User user, Session session) {
        return new SessionMember(user, session, MemberState.JOINED, SessionPermission.MANAGER);
    }

    public static SessionMember registerMember(User user, Session session) {
        return new SessionMember(user, session, MemberState.JOINED, SessionPermission.MEMBER);
    }

    public User getUser() {
        return user;
    }

    public Session getSession() {
        return session;
    }

    public MemberState getState() {
        return state;
    }

    public SessionPermission getSessionPermission() {
        return sessionPermission;
    }

    public void left(){
        state = MemberState.LEFT;
    }
    @Override
    public String getEmail() {
        return user.getEmail();
    }
}
