package com.lcy.ps.user.mapper;

import com.lcy.ps.user.controller.NotNeedAuth.UserController;
import com.lcy.ps.user.domain.User;
import com.lcy.ps.user.dto.response.UserModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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
