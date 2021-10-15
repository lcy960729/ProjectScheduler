package com.lcy.ps.member.domain.invitation;

import com.lcy.ps.core.exception.HasNotPermissionException;
import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.member.dto.request.SendInvitationDTO;
import com.lcy.ps.member.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Invitation invite(long senderId, SendInvitationDTO sendInvitationDTO) {
        final Long projectId = sendInvitationDTO.getProjectId();

        Invitation invitation = Invitation.create(senderId, sendInvitationDTO.getReceiverId(), projectId);
        invitation = invitationRepository.save(invitation);

        // TODO 초대 알림 서비스 추가

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
