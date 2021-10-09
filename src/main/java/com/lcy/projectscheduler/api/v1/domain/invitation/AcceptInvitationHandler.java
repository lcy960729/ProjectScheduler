package com.lcy.projectscheduler.api.v1.domain.invitation;

import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMemberService;
import com.lcy.projectscheduler.authorization.domain.User;
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
