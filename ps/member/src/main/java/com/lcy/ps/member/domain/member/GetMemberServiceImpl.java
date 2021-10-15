package com.lcy.ps.member.domain.member;

import com.lcy.ps.integrate.domain.member.GetMemberService;
import com.lcy.ps.integrate.domain.user.GetUserService;
import com.lcy.ps.integrate.dto.MemberModel;
import com.lcy.ps.integrate.dto.UserModel;
import com.lcy.ps.member.domain.member.projectMember.ProjectMember;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.SessionMember;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.worker.Worker;
import com.lcy.ps.member.repository.ProjectMemberRepository;
import com.lcy.ps.member.repository.SessionMemberRepository;
import com.lcy.ps.member.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetMemberServiceImpl implements GetMemberService {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private SessionMemberRepository sessionMemberRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private GetUserService getUserService;

    @Override
    public List<MemberModel> getProjectMembers(long projectId) {
        List<ProjectMember> projectMembers = projectMemberRepository.findAllByProjectId(projectId);

        List<Long> userIds = projectMembers.stream()
                .map(Member::getUserId)
                .collect(Collectors.toList());

        return getUserService.getUsers(userIds).stream()
                .map(MemberModel::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberModel> getSessionMembers(long sessionId) {
        List<SessionMember> sessionMembers = sessionMemberRepository.findAllBySessionId(sessionId);

        List<Long> userIds = sessionMembers.stream()
                .map(Member::getUserId)
                .collect(Collectors.toList());

        return getUserService.getUsers(userIds).stream()
                .map(MemberModel::of)
                .collect(Collectors.toList());
    }

    @Override
    public MemberModel getWorkers(long workId) {
        Worker worker = workerRepository.findByWorkId(workId);
        UserModel userModel = getUserService.getUser(worker.getUserId());

        return MemberModel.of(userModel);
    }
}
