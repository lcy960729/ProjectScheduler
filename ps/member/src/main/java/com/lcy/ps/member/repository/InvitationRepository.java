package com.lcy.ps.member.repository;

import com.lcy.ps.member.domain.invitation.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

}
