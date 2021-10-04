package com.lcy.projectscheduler.api.v1.dto.request;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddMembersToSessionDTO {
    List<Long> users = new ArrayList<>();

    public List<Long> getUsers() {
        return users;
    }

    public AddMembersToSessionDTO(long user) {
        users.add(user);
    }
}
