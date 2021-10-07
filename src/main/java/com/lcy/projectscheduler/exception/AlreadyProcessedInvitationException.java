package com.lcy.projectscheduler.exception;

public class AlreadyProcessedInvitationException extends BusinessException {
    public AlreadyProcessedInvitationException() {
        super("이미 처리된 초대장 입니다.");
    }
}