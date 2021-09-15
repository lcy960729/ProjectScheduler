package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.repository.ProjectMemberRepository;
import com.lcy.projectscheduler.api.v1.repository.SessionMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SessionMemberService {

    @Autowired
    private SessionMemberRepository sessionMemberRepository;

    @Transactional
    public SessionMember create(SessionMember sessionMember) {
        sessionMember = sessionMemberRepository.save(sessionMember);

        Session session = sessionMember.getSession();
        session.addMember(sessionMember);

        return sessionMember;
    }
}
