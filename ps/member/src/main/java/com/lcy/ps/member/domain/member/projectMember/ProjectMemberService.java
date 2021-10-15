package com.lcy.ps.member.domain.member.projectMember;

import com.lcy.ps.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProjectMemberService {

    @Autowired
    private MemberRepository memberRepository;

    // TODO User  서비스 노출로 구현

    @Transactional
    public ProjectMember registerMember(long userId, long projectId) {

        ProjectMember projectMember = ProjectMember.registerMember(userId, projectId);

        projectMember = memberRepository.save(projectMember);

        // TODO 가입 알림 메시지 전송

        return projectMember;
    }

    @Transactional
    public ProjectMember registerSuperManager(long userId, long projectId) {

        ProjectMember projectMember = ProjectMember.registerSuperManager(userId, projectId);

        projectMember = memberRepository.save(projectMember);

        return projectMember;
    }
}
