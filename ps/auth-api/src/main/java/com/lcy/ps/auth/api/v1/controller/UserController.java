package com.lcy.ps.auth.api.v1.controller;

import com.lcy.ps.auth.api.v1.domain.user.UserService;
import com.lcy.ps.core.enums.SecurityHeaders;
import com.lcy.ps.auth.api.v1.mapper.UserModelAssembler;
import com.lcy.ps.core.domain.user.User;
import com.lcy.ps.core.dto.request.login.SignInDTO;
import com.lcy.ps.core.dto.request.login.SignUpDTO;
import com.lcy.ps.core.dto.response.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @PostMapping(path = "signIn")
    public ResponseEntity<UserModel> signIn(@RequestBody SignInDTO signInDTO) {
        String token = userService.signIn(signInDTO);
        String authorizationHeader = SecurityHeaders.AUTHORIZATION.getName();

        return ResponseEntity.ok().header(authorizationHeader, token).build();
    }

    @PostMapping(path = "signUp")
    public ResponseEntity<UserModel> signUp(@RequestBody SignUpDTO signUpDTO) {
        User user = userService.signUp(signUpDTO);
        UserModel userModel = userModelAssembler.toModel(user);

        return ResponseEntity.ok(userModel);
    }
}
