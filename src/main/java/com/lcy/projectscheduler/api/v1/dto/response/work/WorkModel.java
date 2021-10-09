package com.lcy.projectscheduler.api.v1.dto.response.work;

import com.lcy.projectscheduler.api.v1.domain.project.session.work.Work;
import com.lcy.projectscheduler.api.v1.domain.project.session.work.WorkState;
import com.lcy.projectscheduler.api.v1.domain.project.session.work.Worker;
import com.lcy.projectscheduler.authorization.dto.UserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkModel extends RepresentationModel<WorkModel> {
    private Long id;
    private String title;
    private String content;
    private Integer priority;
    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;
    private WorkState state;
    private Set<UserModel> workers = new HashSet<>();

    private WorkModel(Long id, String title, String content, LocalDateTime startDate, LocalDateTime deadlineDate, Set<Worker> workers, WorkState state) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.state = state;

        this.workers = workers.stream()
                .map(UserModel::of)
                .collect(Collectors.toSet());
    }

    public static WorkModel of(Work work) {
        return new WorkModel(
                work.getId(),
                work.getTitle(),
                work.getContent(),
                work.getStartDate(),
                work.getDeadlineDate(),
                work.getWorkers(),
                work.getState());
    }
}
