package com.lcy.ps.integrate.domain.user;

import com.lcy.ps.integrate.dto.UserModel;

import java.util.List;

public interface GetUserService {
    List<UserModel> getUsers(List<Long> userIds);

    UserModel getUser(Long userId);
}
