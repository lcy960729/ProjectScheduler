package com.lcy.projectscheduler.api.v1.domain.invitation;

import com.lcy.projectscheduler.api.v1.domain.Member.permission.Permission;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.repository.InvitationRepository;
import com.lcy.projectscheduler.api.v1.repository.ProjectMemberRepository;
import com.lcy.projectscheduler.api.v1.repository.UserRepository;
import com.lcy.projectscheduler.exception.AlreadyProcessedInvitationException;
import com.lcy.projectscheduler.exception.HasNotPermissionException;
import com.lcy.projectscheduler.exception.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Invitation invite(long senderId, long receiverId, long projectId) {
        ProjectMember projectMember = projectMemberRepository.findByUserIdAndProjectId(senderId, projectId)
                .orElseThrow(HasNotPermissionException::new);

        projectMember.auth(Permission.INVITE);

        User sender = projectMember.getUser();

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(NotFoundEntityException::new);

        Invitation invitation = Invitation.of(sender, receiver, projectMember.getProject());
        invitation = invitationRepository.save(invitation);

        return invitation;
    }

    @Transactional
    public void accept(long receiverId, long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(NotFoundEntityException::new);

        if (invitation.isNotSameReceiverId(receiverId)) {
            throw new HasNotPermissionException();
        }

        if (invitation.isNotWaiting()) {
            throw new AlreadyProcessedInvitationException();
        }

        invitation.accept(publisher);

        invitationRepository.save(invitation);
    }

    @Transactional
    public void reject(long receiverId, long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(NotFoundEntityException::new);

        if (invitation.isNotSameReceiverId(receiverId)) {
            throw new HasNotPermissionException();
        }

        if (invitation.isNotWaiting()) {
            throw new AlreadyProcessedInvitationException();
        }

        invitation.reject();

        invitationRepository.save(invitation);
    }
}
