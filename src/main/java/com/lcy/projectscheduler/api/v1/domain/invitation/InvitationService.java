package com.lcy.projectscheduler.api.v1.domain.invitation;

import com.lcy.projectscheduler.api.v1.domain.member.permission.Permission;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMember;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectMemberService;
import com.lcy.projectscheduler.exception.HasNotPermissionException;
import com.lcy.projectscheduler.exception.NotFoundEntityException;
import com.lcy.projectscheduler.security.domain.User;
import com.lcy.projectscheduler.api.v1.repository.InvitationRepository;
import com.lcy.projectscheduler.security.repository.UserRepository;
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
    private ProjectMemberService projectMemberService;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Invitation invite(long senderId, long receiverId, long projectId) {
        ProjectMember projectMember = projectMemberService.get(senderId, projectId);
        projectMember.checkRegisteredAndPermission(Permission.INVITE);

        User sender = projectMember.getUser();

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(NotFoundEntityException::new);

        Invitation invitation = Invitation.of(sender, receiver, projectMember.getProject());
        invitation = invitationRepository.save(invitation);

        return invitation;
    }

    @Transactional
    public Invitation accept(long receiverId, long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(NotFoundEntityException::new);

        if (invitation.isNotSameReceiverId(receiverId)) {
            throw new HasNotPermissionException();
        }

        invitation.accept(publisher);

        invitation = invitationRepository.save(invitation);

        return invitation;
    }

    @Transactional
    public Invitation reject(long receiverId, long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(NotFoundEntityException::new);

        if (invitation.isNotSameReceiverId(receiverId)) {
            throw new HasNotPermissionException();
        }

        invitation.reject();

        invitation = invitationRepository.save(invitation);
        return invitation;
    }
}
