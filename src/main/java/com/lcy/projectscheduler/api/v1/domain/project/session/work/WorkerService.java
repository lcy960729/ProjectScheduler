package com.lcy.projectscheduler.api.v1.domain.project.session.work;

import com.lcy.projectscheduler.api.v1.domain.project.session.SessionMember;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.repository.WorkerRepository;
import com.lcy.projectscheduler.exception.NotRegisteredMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    public Worker createWorker(User user, Work work) {
        return create(Worker.registerWorker(user, work));
    }

    public Worker createCoworker(User user, Work work) {
        return create(Worker.registerCoworker(user, work));
    }

    private Worker create(Worker worker) {
        worker = workerRepository.save(worker);

        Work work = worker.getWork();
        work.addCoworker(worker);

        return worker;
    }

    public Worker get(long userId, long workId) {
        return workerRepository.findByUserIdAndWorkId(userId, workId)
                .orElseThrow(NotRegisteredMemberException::new);
    }
}
