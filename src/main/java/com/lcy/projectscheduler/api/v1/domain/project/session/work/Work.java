package com.lcy.projectscheduler.api.v1.domain.project.session.work;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import com.lcy.projectscheduler.api.v1.dto.CreateWorkDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Work extends BaseEntity {
    @Column
    private String title;
    @Column
    private String content;

    @Column
    private Integer priority;

    @ManyToOne
    @JoinColumn
    private Session session;

    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime deadlineDate;

    @Column
    @Enumerated(EnumType.STRING)
    private WorkState state;

    @OneToMany(mappedBy = "work")
    private Set<Worker> workers = new HashSet<>();

    public Work(Session session, String title, String content, Integer priority, LocalDateTime startDate, LocalDateTime deadlineDate, WorkState state) {
        this.session = session;
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.state = state;
    }

    public static Work of(Session session, CreateWorkDTO dto) {
        return new Work(session, dto.getTitle(), dto.getContents(), dto.getPriority(), dto.getStartDate(), dto.getDeadlineDate(), WorkState.NOT_STARTED);
    }

    public void addCoworker(Worker coworker) {
        workers.add(coworker);
    }

    public Set<Worker> getWorkers() {
        return workers;
    }
}
