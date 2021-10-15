package com.lcy.ps.integrate.domain.member;


import com.lcy.ps.integrate.dto.MemberModel;

public interface RegisterMemberService {
    MemberModel registerProjectMember(long userId, long projectId);

    MemberModel registerSessionMember(long userId, long sessionId);

    MemberModel registerWorker(long userId, long workId);

    MemberModel registerProjectManager(long userId, long projectId);

    MemberModel registerSessionManager(long userId, long sessionId);
}
