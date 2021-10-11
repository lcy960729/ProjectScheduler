package com.lcy.ps.core.domain.project.session.work;

import com.lcy.ps.core.domain.base.BaseEntity;
import com.lcy.ps.core.domain.project.session.Session;
import com.lcy.ps.core.dto.request.work.CreateWorkDTO;
import com.lcy.ps.core.dto.request.work.UpdateWorkDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "work")
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

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getPriority() {
        return priority;
    }

    public Session getSession() {
        return session;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public WorkState getState() {
        return state;
    }

    public void update(UpdateWorkDTO updateWorkDTO) {
        this.title = updateWorkDTO.getTitle();
        this.content = updateWorkDTO.getContents();
        this.priority = updateWorkDTO.getPriority();
        this.startDate = updateWorkDTO.getStartDate();
        this.deadlineDate = updateWorkDTO.getDeadlineDate();
    }
}
