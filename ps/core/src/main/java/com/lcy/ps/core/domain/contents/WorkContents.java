package com.lcy.ps.core.domain.contents;

import com.lcy.ps.core.domain.project.Project;
import com.lcy.ps.core.domain.project.session.work.Work;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("W")
public class WorkContents extends Contents {
    @OneToOne
    private Work work;

    public long getWorkId(){
        return work.getId();
    }
}
