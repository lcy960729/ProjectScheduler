package com.lcy.ps.contents.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class SessionContents extends Contents {
    @Column(name = "session_id")
    private Long sessionId;

    public Long getSessionId() {
        return sessionId;
    }
}
