package com.lcy.ps.core.domain.contents;

import com.lcy.ps.core.domain.project.Project;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("P")
public class ProjectContents extends Contents {
    @OneToOne
    private Project project;

    public long getProjectId(){
        return project.getId();
    }
}
