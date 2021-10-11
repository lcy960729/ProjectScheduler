package com.lcy.ps.main.api.v1.repository;

import com.lcy.ps.main.api.v1.domain.invitation.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

}
