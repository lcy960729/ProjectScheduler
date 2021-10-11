package com.lcy.ps.core.domain.contents;

import com.lcy.ps.core.domain.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Contents extends BaseEntity {
    private String data;

    public void setData(String data) {
        this.data = data;
    }
}