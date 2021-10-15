package com.lcy.ps.project.dto.response.work;

import com.lcy.ps.integrate.dto.MemberModel;
import com.lcy.ps.project.domain.session.work.Work;
import com.lcy.ps.project.domain.session.work.WorkState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

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
    private MemberModel worker;

    private WorkModel(Long id, String title, String content, LocalDateTime startDate, LocalDateTime deadlineDate, WorkState state) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.state = state;
    }

    public static WorkModel of(Work work) {
        return new WorkModel(work.getId(), work.getTitle(), work.getContent(), work.getStartDate(), work.getDeadlineDate(), WorkState.NOT_STARTED);
    }

    public void setWorker(MemberModel worker) {
        this.worker = worker;
    }
}
