package com.lcy.ps.auth.api.v1.mapper;

import com.lcy.ps.auth.api.v1.controller.UserController;
import com.lcy.ps.core.domain.user.User;
import com.lcy.ps.core.dto.response.user.UserModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User user) {
        return UserModel.of(user);
    }
}
