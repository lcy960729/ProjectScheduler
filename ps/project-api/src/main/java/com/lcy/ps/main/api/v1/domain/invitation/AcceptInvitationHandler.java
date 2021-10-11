package com.lcy.ps.main.api.v1.domain.invitation;

import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.domain.project.ProjectMember;
import com.lcy.ps.core.domain.user.User;
import com.lcy.ps.main.api.v1.domain.project.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AcceptInvitationHandler implements ApplicationListener<AcceptInvitationEvent> {
    @Autowired
    private ProjectMemberService projectMemberService;

    @Override
    public void onApplicationEvent(AcceptInvitationEvent event) {
        User user = event.getUser();
        Project project = event.getProject();

        ProjectMember projectMember = ProjectMember.registerMember(user, project);

        projectMemberService.create(projectMember);
    }
}
