package com.lcy.ps.project.dto.response.session;

import com.lcy.ps.integrate.dto.MemberModel;
import com.lcy.ps.project.domain.session.Session;
import com.lcy.ps.project.domain.session.SessionState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SessionModel extends RepresentationModel<SessionModel> {
    private Long id;
    private String title;
    private String subject;
    private LocalDateTime startDate;
    private LocalDateTime deadlineDate;
    private List<MemberModel> members = new ArrayList<>();
    private SessionState state;

    private SessionModel(Long id, String title, String subject, LocalDateTime startDate, LocalDateTime deadlineDate, SessionState state) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.state = state;
    }

    public static SessionModel of(Session session) {
        return new SessionModel(session.getId(), session.getTitle(), session.getSubject(), session.getStartDate(), session.getDeadlineDate(), SessionState.NOT_STARTED);
    }

    public void setMembers(List<MemberModel> members) {
        this.members = members;
    }

}
