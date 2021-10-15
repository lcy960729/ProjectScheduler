package com.lcy.ps.integrate.domain.member;


import com.lcy.ps.integrate.dto.MemberModel;

import java.util.List;

public interface GetMemberService {
    List<MemberModel> getProjectMembers(long projectId);

    List<MemberModel> getSessionMembers(long sessionId);

    MemberModel getWorkers(long workId);
}
