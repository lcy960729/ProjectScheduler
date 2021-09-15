package com.lcy.projectscheduler.api.v1.domain.project.session;

import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMemberService;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.dto.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.dto.CreateSessionDTO;
import com.lcy.projectscheduler.api.v1.repository.ProjectMemberRepository;
import com.lcy.projectscheduler.api.v1.repository.ProjectRepository;
import com.lcy.projectscheduler.api.v1.repository.SessionRepository;
import com.lcy.projectscheduler.api.v1.repository.UserRepository;
import com.lcy.projectscheduler.exception.HasNotPermissionException;
import com.lcy.projectscheduler.exception.NotFoundEntityException;
import com.lcy.projectscheduler.exception.NotRegisteredMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionMemberService sessionMemberService;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Transactional
    public Session create(long projectId, CreateSessionDTO createSessionDTO) {
        final long userId = createSessionDTO.getManager();

        ProjectMember projectMember = projectMemberRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(NotRegisteredMemberException::new);

        projectMember.auth(Permission.CREATE);

        User user = projectMember.getUser();
        Project project = projectMember.getProject();

        Session session = Session.of(project, createSessionDTO);
        session = sessionRepository.save(session);

        SessionMember manager = SessionMember.registerManager(user, session);

        sessionMemberService.create(manager);

        return session;
    }
}
