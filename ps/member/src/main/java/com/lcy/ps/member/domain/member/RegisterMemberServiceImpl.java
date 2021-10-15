package com.lcy.ps.member.domain.member;

import com.lcy.ps.integrate.domain.member.RegisterMemberService;
import com.lcy.ps.integrate.domain.user.GetUserService;
import com.lcy.ps.integrate.dto.MemberModel;
import com.lcy.ps.integrate.dto.UserModel;
import com.lcy.ps.member.domain.member.projectMember.ProjectMember;
import com.lcy.ps.member.domain.member.projectMember.ProjectMemberService;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.SessionMember;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.SessionMemberService;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.worker.Worker;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegisterMemberServiceImpl implements RegisterMemberService {

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private SessionMemberService sessionMemberService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private GetUserService getUserService;

    @Override
    @Transactional
    public MemberModel registerProjectMember(long userId, long projectId) {
        ProjectMember projectMember = projectMemberService.registerMember(userId, projectId);

        UserModel userModel = getUserService.getUser(projectMember.getUserId());

        return MemberModel.of(userModel);
    }

    @Override
    @Transactional
    public MemberModel registerSessionMember(long projectMemberId, long sessionId) {
        SessionMember sessionMember = sessionMemberService.registerMember(projectMemberId, sessionId);

        UserModel userModel = getUserService.getUser(sessionMember.getUserId());

        return MemberModel.of(userModel);
    }

    @Override
    @Transactional
    public MemberModel registerWorker(long sessionMemberID, long workId) {
        Worker worker = workerService.registerWorker(sessionMemberID, workId);

        UserModel userModel = getUserService.getUser(worker.getUserId());

        return MemberModel.of(userModel);
    }

    @Override
    @Transactional
    public MemberModel registerProjectManager(long userId, long projectId) {
        ProjectMember projectMember = projectMemberService.registerSuperManager(userId, projectId);

        UserModel userModel = getUserService.getUser(projectMember.getUserId());

        return MemberModel.of(userModel);
    }

    @Override
    @Transactional
    public MemberModel registerSessionManager(long projectMemberId, long sessionId) {
        SessionMember sessionMember = sessionMemberService.registerManager(projectMemberId, sessionId);

        UserModel userModel = getUserService.getUser(sessionMember.getUserId());

        return MemberModel.of(userModel);
    }
}
