package com.lcy.ps.member.dto.request;

import lombok.Data;

@Data
public class SendInvitationDTO {
    private Long receiverId;
    private Long projectId;
}
