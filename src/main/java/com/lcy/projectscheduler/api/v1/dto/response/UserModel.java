package com.lcy.projectscheduler.api.v1.dto.response;

import com.lcy.projectscheduler.api.v1.domain.member.Member;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UserModel extends RepresentationModel<UserModel> {
    private String email;

    private UserModel(String email) {
        this.email = email;
    }

    public static UserModel of(Member member) {
        return new UserModel(member.getEmail());
    }

}
