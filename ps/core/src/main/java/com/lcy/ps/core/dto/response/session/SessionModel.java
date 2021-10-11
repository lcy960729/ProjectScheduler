package com.lcy.ps.core.dto.response.session;

import com.lcy.ps.core.domain.project.session.Session;
import com.lcy.ps.core.domain.project.session.SessionMember;
import com.lcy.ps.core.domain.project.session.SessionState;
import com.lcy.ps.core.dto.response.user.UserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
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
