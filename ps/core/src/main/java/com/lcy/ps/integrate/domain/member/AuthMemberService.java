package com.lcy.ps.integrate.domain.member;


public interface AuthMemberService {
    void auth(long memberId, Permission permission);
}
