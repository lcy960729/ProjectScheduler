package com.lcy.projectscheduler.api.v1.dto.request.session;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddMembersToSessionDTO {
    private List<Long> users = new ArrayList<>();

    public AddMembersToSessionDTO(long user) {
        users.add(user);
    }
}
