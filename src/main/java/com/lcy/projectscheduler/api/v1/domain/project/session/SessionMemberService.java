package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMemberService;
import com.lcy.projectscheduler.security.domain.User;
import com.lcy.projectscheduler.api.v1.repository.SessionMemberRepository;
import com.lcy.projectscheduler.security.repository.UserRepository;
import com.lcy.projectscheduler.exception.NotRegisteredMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SessionMemberService {

    @Autowired
    private SessionMemberRepository sessionMemberRepository;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private UserRepository userRepository;

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
