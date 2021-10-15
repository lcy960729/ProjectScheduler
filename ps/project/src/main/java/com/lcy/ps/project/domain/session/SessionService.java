package com.lcy.ps.project.domain.session;

import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.integrate.domain.member.GetMemberService;
import com.lcy.ps.integrate.domain.member.RegisterMemberService;
import com.lcy.ps.integrate.dto.MemberModel;
import com.lcy.ps.project.annotation.MemberID;
import com.lcy.ps.project.annotation.NeedPermission;
import com.lcy.ps.project.domain.Project;
import com.lcy.ps.project.dto.request.session.CreateSessionDTO;
import com.lcy.ps.project.dto.request.session.UpdateSessionDTO;
import com.lcy.ps.integrate.domain.member.Permission;
import com.lcy.ps.project.dto.response.session.SessionModel;
import com.lcy.ps.project.mapper.SessionModelAssembler;
import com.lcy.ps.project.repository.ProjectRepository;
import com.lcy.ps.project.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RegisterMemberService registerMemberService;

    @Autowired
    private GetMemberService getMemberService;

    @Autowired
    private SessionModelAssembler sessionModelAssembler;

    @Transactional
    @NeedPermission(Permission.CREATE)
    public SessionModel create(@MemberID long memberId, long projectId, CreateSessionDTO createSessionDTO) {
        Project project = projectRepository.getById(projectId);

        Session session = Session.of(project, createSessionDTO);
        session = sessionRepository.save(session);

        MemberModel manager = registerMemberService.registerSessionManager(memberId, session.getId());

        SessionModel sessionModel = sessionModelAssembler.toModel(session);
        sessionModel.getMembers().add(manager);

        return sessionModel;
    }

    @Transactional(readOnly = true)
    @NeedPermission(Permission.READ)
    public SessionModel getSession(@MemberID long memberId, long sessionId) {
        Session session = getSession(sessionId);

        return toSessionModel(session);
    }


    @Transactional(readOnly = true)
    @NeedPermission(Permission.READ)
    public List<SessionModel> getAll(long memberId, long projectId) {

        return sessionRepository.findAllByProjectId(projectId).stream()
                .map(this::toSessionModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @NeedPermission(Permission.UPDATE)
    public SessionModel update(@MemberID long memberId, long sessionId, UpdateSessionDTO updateSessionDTO) {
        Session session = getSession(sessionId);

        session.update(updateSessionDTO);
        session = sessionRepository.save(session);

        return toSessionModel(session);
    }

    @Transactional
    @NeedPermission(Permission.DELETE)
    public void delete(@MemberID long memberId, long sessionId) {
        sessionRepository.deleteById(sessionId);
    }


    private Session getSession(long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(NotFoundEntityException::new);
    }

    private SessionModel toSessionModel(Session session) {
        List<MemberModel> members = getMemberService.getSessionMembers(session.getId());

        SessionModel sessionModel = sessionModelAssembler.toModel(session);
        sessionModel.setMembers(members);

        return sessionModel;
    }
}
