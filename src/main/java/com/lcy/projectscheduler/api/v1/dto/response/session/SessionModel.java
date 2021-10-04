package com.lcy.projectscheduler.api.v1.dto.response.session;

import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import com.lcy.projectscheduler.api.v1.domain.project.session.SessionMember;
import com.lcy.projectscheduler.api.v1.domain.project.session.SessionState;
import com.lcy.projectscheduler.api.v1.dto.request.session.CreateSessionDTO;
import com.lcy.projectscheduler.api.v1.dto.response.UserModel;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SessionModel extends RepresentationModel<SessionModel> {
    private Long id;
    private String title;
    private String subject;
    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;
    private Set<UserModel> sessionMembers = new HashSet<>();
    private SessionState state;

    private SessionModel(Long id, String title, String subject, LocalDateTime startDate, LocalDateTime deadlineDate, Set<SessionMember> sessionMembers, SessionState state) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.state = state;

        this.sessionMembers = sessionMembers.stream()
                .map(UserModel::of)
                .collect(Collectors.toSet());
    }

    public static SessionModel of(Session session) {
        return new SessionModel(
                session.getId(),
                session.getTitle(),
                session.getSubject(),
                session.getStartDate(),
                session.getDeadlineDate(),
                session.getSessionMembers(),
                session.getState());
    }
}
