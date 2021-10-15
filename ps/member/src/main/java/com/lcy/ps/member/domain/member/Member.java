package com.lcy.ps.member.domain.member;

import com.lcy.ps.core.domain.BaseEntity;
import com.lcy.ps.integrate.domain.member.Permission;
import com.lcy.ps.member.config.MemberPermissionJPAConverter;
import com.lcy.ps.member.domain.member.permission.MemberPermission;
import com.lcy.ps.member.domain.member.state.MemberState;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor
public class Member extends BaseEntity {

    @Column
    private Long userId;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberState state;

    @Column
    @Convert(converter = MemberPermissionJPAConverter.class)
    private MemberPermission permission;

    public MemberPermission getPermission() {
        return permission;
    }

    public Member(long user, MemberState state, MemberPermission permission) {
        this.userId = user;
        this.state = state;
        this.permission = permission;
    }

    public void checkRegisteredAndPermission(Permission needPermission) {
        state.checkRegistered();
        permission.checkPermission(needPermission);
    }

    public MemberState getState() {
        return state;
    }

    public Long getUserId() {
        return userId;
    }
}
