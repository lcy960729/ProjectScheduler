package com.lcy.ps.project.domain.session.work;

import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.integrate.domain.member.GetMemberService;
import com.lcy.ps.integrate.domain.member.RegisterMemberService;
import com.lcy.ps.integrate.dto.MemberModel;
import com.lcy.ps.project.annotation.MemberID;
import com.lcy.ps.project.annotation.NeedPermission;
import com.lcy.ps.project.domain.session.Session;
import com.lcy.ps.project.dto.request.work.CreateWorkDTO;
import com.lcy.ps.project.dto.request.work.UpdateWorkDTO;
import com.lcy.ps.integrate.domain.member.Permission;
import com.lcy.ps.project.dto.response.work.WorkModel;
import com.lcy.ps.project.mapper.WorkModelAssembler;
import com.lcy.ps.project.repository.SessionRepository;
import com.lcy.ps.project.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private GetMemberService getMemberService;

    @Autowired
    private RegisterMemberService registerMemberService;

    @Autowired
    private WorkModelAssembler workModelAssembler;

    @Transactional
    @NeedPermission(Permission.CREATE)
    public WorkModel create(@MemberID long memberId, long sessionId, CreateWorkDTO createWorkDTO) {
        Session session = sessionRepository.getById(sessionId);
        Work work = Work.of(session, createWorkDTO);

        work = workRepository.save(work);

        MemberModel worker = registerMemberService.registerWorker(memberId, work.getId());

        WorkModel workModel = workModelAssembler.toModel(work);
        workModel.setWorker(worker);

        return workModel;
    }

    @Transactional(readOnly = true)
    @NeedPermission(Permission.CREATE)
    public WorkModel get(@MemberID long memberId, long workId) {
        Work work = getWork(workId);

        return toWorkModel(work);
    }


    @Transactional(readOnly = true)
    public List<WorkModel> getAll(long sessionId) {
        return workRepository.findAllBySessionId(sessionId).stream()
                .map(this::toWorkModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @NeedPermission(Permission.UPDATE)
    public WorkModel update(@MemberID long memberId, long workId, UpdateWorkDTO updateWorkDTO) {
        Work work = getWork(workId);
        work.update(updateWorkDTO);
        work = workRepository.save(work);

        return toWorkModel(work);
    }

    @Transactional
    @NeedPermission(Permission.DELETE)
    public void delete(@MemberID long memberId, long workId) {
        workRepository.deleteById(workId);
    }

    private Work getWork(long workId) {
        return workRepository.findById(workId)
                .orElseThrow(NotFoundEntityException::new);
    }

    private WorkModel toWorkModel(Work work) {
        MemberModel worker = getMemberService.getWorkers(work.getId());

        WorkModel workModel = workModelAssembler.toModel(work);
        workModel.setWorker(worker);

        return workModel;
    }
}
