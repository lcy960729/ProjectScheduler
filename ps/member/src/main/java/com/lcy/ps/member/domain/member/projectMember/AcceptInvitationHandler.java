package com.lcy.ps.member.domain.member.projectMember;

import com.lcy.ps.member.domain.invitation.AcceptInvitationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class AcceptInvitationHandler implements ApplicationListener<AcceptInvitationEvent> {
    @Autowired
    private ProjectMemberService projectMemberService;

    @Override
    public void onApplicationEvent(AcceptInvitationEvent event) {
        projectMemberService.registerMember(event.getUser(), event.getProject());
    }
}
