package com.lcy.ps.member.domain.member.projectMember.sessionMember.worker;

import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.member.domain.member.projectMember.sessionMember.SessionMember;
import com.lcy.ps.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    @Autowired
    private MemberRepository memberRepository;

    public Worker registerWorker(long sessionMemberId, long workId) {
        SessionMember sessionMember = (SessionMember) memberRepository.findById(sessionMemberId)
                .orElseThrow(NotFoundEntityException::new);

        Worker worker = Worker.registerWorker(workId, sessionMember);
        worker = memberRepository.save(worker);

        return worker;
    }
}
