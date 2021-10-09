package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMemberService;
import com.lcy.projectscheduler.authorization.domain.User;
import com.lcy.projectscheduler.api.v1.dto.request.session.AddMembersToSessionDTO;
import com.lcy.projectscheduler.api.v1.dto.request.session.CreateSessionDTO;
import com.lcy.projectscheduler.api.v1.dto.request.session.UpdateSessionDTO;
import com.lcy.projectscheduler.api.v1.repository.SessionRepository;
import com.lcy.projectscheduler.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionMemberService sessionMemberService;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Transactional
    public Session create(long userId, long projectId, CreateSessionDTO createSessionDTO) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.CREATE);

        User user = projectMember.getUser();
        Project project = projectMember.getProject();

        Session session = Session.of(project, createSessionDTO);
        session = sessionRepository.save(session);

        sessionMemberService.createManager(user, session);

        return session;
    }

    public Session get(long userId, long projectId, long sessionId) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.READ);

        return sessionRepository.findById(sessionId)
                .orElseThrow(NotFoundEntityException::new);
    }

    @Transactional
    public Session update(long userId, long projectId, long sessionId, UpdateSessionDTO updateSessionDTO) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.READ);

        SessionMember sessionMember = sessionMemberService.get(userId, sessionId);
        sessionMember.checkRegisteredAndPermission(Permission.UPDATE);

        Session session = sessionMember.getSession();

        session.update(updateSessionDTO);

        session = sessionRepository.save(session);

        return session;
    }

    @Transactional
    public Session addMembers(long userId, long projectId, long sessionId, AddMembersToSessionDTO addMembersToSessionDTO) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.READ);

        SessionMember sessionMember = sessionMemberService.get(userId, sessionId);
        sessionMember.checkRegisteredAndPermission(Permission.INVITE);

        Session session = sessionMember.getSession();

        List<Long> userList = addMembersToSessionDTO.getUsers();

        getNotJoinedUsers(userList, session)
                .forEach(user -> sessionMemberService.createMember(user, session));

        return session;
    }

    private Stream<Long> getNotJoinedUsers(List<Long> users, Session session) {
        return users.stream().filter(session::isNotJoined);
    }

    @Transactional
    public Session kickOutMember(long userId, long projectId, long sessionId, long kickOutMemberId) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.READ);

        SessionMember manager = sessionMemberService.get(userId, sessionId);
        manager.checkRegisteredAndPermission(Permission.INVITE);

        SessionMember kickOutMember = sessionMemberService.get(kickOutMemberId, sessionId);
        kickOutMember.checkRegisteredAndPermission(Permission.READ);

        Session session = manager.getSession();

        sessionMemberService.left(kickOutMember);

        return session;
    }

    @Transactional
    public Session setState(long userId, long projectId, long sessionId, SessionState state) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.READ);

        SessionMember manager = sessionMemberService.get(userId, sessionId);
        manager.checkRegisteredAndPermission(Permission.INVITE);

        Session session = manager.getSession();

        session.setState(state);

        session = sessionRepository.save(session);

        return session;
    }

    public void delete(long userId, long projectId, long sessionId) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.READ);

        SessionMember manager = sessionMemberService.get(userId, sessionId);
        manager.checkRegisteredAndPermission(Permission.DELETE);

        sessionRepository.deleteById(sessionId);
    }
}
