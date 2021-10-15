package com.lcy.ps.contents.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("W")
public class WorkContents extends Contents {

    @Column(name = "work_id")
    private Long workId;

    public Long getWorkId() {
        return workId;
    }
}
