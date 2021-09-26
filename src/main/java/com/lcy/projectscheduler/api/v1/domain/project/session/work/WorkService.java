package com.lcy.projectscheduler.api.v1.domain.project.session.work;

import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMemberService;
import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import com.lcy.projectscheduler.api.v1.domain.project.session.SessionMember;
import com.lcy.projectscheduler.api.v1.domain.project.session.SessionMemberService;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.dto.CreateWorkDTO;
import com.lcy.projectscheduler.api.v1.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private SessionMemberService sessionMemberService;

    @Autowired
    private WorkerService workerService;

    public Work create(long userId, long projectId, long sessionId, CreateWorkDTO createWorkDTO) {
        ProjectMember projectMember = projectMemberService.get(userId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.READ);

        SessionMember sessionMember = sessionMemberService.get(userId, sessionId);
        sessionMember.checkRegisteredAndPermission(Permission.CREATE);

        User user = sessionMember.getUser();
        Session session = sessionMember.getSession();

        Work work = Work.of(session, createWorkDTO);
        work = workRepository.save(work);

        workerService.createWorker(user, work);

        return work;
    }
}
