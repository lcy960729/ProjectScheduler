package com.lcy.projectscheduler.authorization.dto;

import com.lcy.projectscheduler.authorization.controller.UserController;
import com.lcy.projectscheduler.authorization.domain.User;
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
