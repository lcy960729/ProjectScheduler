package com.lcy.ps.user.dto.response;

import com.lcy.ps.user.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserModel extends RepresentationModel<UserModel> {
    private String email;

    private UserModel(String email) {
        this.email = email;
    }

    public static UserModel of(User user) {
        return new UserModel(user.getEmail());
    }
}
