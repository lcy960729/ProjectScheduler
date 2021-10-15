package com.lcy.ps.contents.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class ProjectContents extends Contents {
    @Column(name = "project_id")
    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }
}
