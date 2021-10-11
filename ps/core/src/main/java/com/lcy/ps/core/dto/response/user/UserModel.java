package com.lcy.ps.core.dto.response.user;

import com.lcy.ps.core.domain.member.Member;
import com.lcy.ps.core.domain.user.User;
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

    public static UserModel of(User user) {
        return new UserModel(user.getEmail());
    }
}
