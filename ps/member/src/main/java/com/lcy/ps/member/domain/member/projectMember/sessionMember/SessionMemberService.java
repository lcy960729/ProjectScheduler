package com.lcy.ps.member.domain.member.projectMember.sessionMember;

import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.member.domain.member.projectMember.ProjectMember;
import com.lcy.ps.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SessionMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public SessionMember registerManager(long projectMemberId, long sessionId) {
        ProjectMember projectMember = (ProjectMember) memberRepository.findById(projectMemberId)
                .orElseThrow(NotFoundEntityException::new);

        SessionMember sessionMember = SessionMember.registerManager(sessionId, projectMember);
        sessionMember = memberRepository.save(sessionMember);

        return sessionMember;
    }

    @Transactional
    public SessionMember registerMember(long projectMemberId, long sessionId) {
        ProjectMember projectMember = (ProjectMember) memberRepository.findById(projectMemberId)
                .orElseThrow(NotFoundEntityException::new);

        SessionMember sessionMember = SessionMember.registerMember(sessionId, projectMember);
        sessionMember = memberRepository.save(sessionMember);

        return sessionMember;
    }
}
