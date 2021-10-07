package com.lcy.projectscheduler.security.dto;

import com.lcy.projectscheduler.security.controller.UserController;
import com.lcy.projectscheduler.security.domain.User;
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
