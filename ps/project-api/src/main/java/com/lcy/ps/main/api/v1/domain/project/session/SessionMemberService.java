package com.lcy.ps.main.api.v1.domain.project.session;

import com.lcy.ps.core.domain.member.permission.Permission;
import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.domain.project.ProjectMember;
import com.lcy.ps.core.domain.project.session.Session;
import com.lcy.ps.core.domain.project.session.SessionMember;
import com.lcy.ps.core.domain.user.User;
import com.lcy.ps.core.exception.NotRegisteredMemberException;
import com.lcy.ps.core.repository.SessionMemberRepository;
import com.lcy.ps.main.api.v1.domain.project.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SessionMemberService {

    @Autowired
    private SessionMemberRepository sessionMemberRepository;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Transactional
    public SessionMember createManager(User user, Session session) {
        return create(SessionMember.registerManager(user, session));
    }

    @Transactional
    public SessionMember createMember(User user, Session session) {
        return create(SessionMember.registerMember(user, session));
    }

    @Transactional
    public SessionMember createMember(long userId, Session session) {
        Project project = session.getProject();

        ProjectMember projectMember = projectMemberService.get(userId, project.getId());
        projectMember.checkRegisteredAndPermission(Permission.READ);

        User user = projectMember.getUser();

        return create(SessionMember.registerMember(user, session));
    }

    private SessionMember create(SessionMember sessionMember) {
        sessionMember = sessionMemberRepository.save(sessionMember);

        Session session = sessionMember.getSession();
        session.addMember(sessionMember);

        return sessionMember;
    }

    public SessionMember get(long userId, long sessionId) {
        return sessionMemberRepository.findByUserIdAndSessionId(userId, sessionId)
                .orElseThrow(NotRegisteredMemberException::new);
    }

    public void left(SessionMember sessionMember) {
        sessionMember.left();
        sessionMember = sessionMemberRepository.save(sessionMember);
    }
}
