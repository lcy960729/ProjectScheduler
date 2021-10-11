package com.lcy.ps.core.domain.contents;

import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.domain.project.session.Session;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("S")
public class SessionContents extends Contents {
    @OneToOne
    private Session session;

    public long getSessionId(){
        return session.getId();
    }
}
