package com.lcy.ps.core.dto.request.session;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddMembersToSessionDTO {
    private List<Long> users = new ArrayList<>();

    public AddMembersToSessionDTO(long user) {
        users.add(user);
    }
}
