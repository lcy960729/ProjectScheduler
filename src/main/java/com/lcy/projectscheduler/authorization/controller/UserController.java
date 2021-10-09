package com.lcy.projectscheduler.authorization.controller;

import com.lcy.projectscheduler.api.v1.dto.request.login.SignUpDTO;
import com.lcy.projectscheduler.authorization.SecurityHeaders;
import com.lcy.projectscheduler.authorization.domain.User;
import com.lcy.projectscheduler.api.v1.dto.request.login.SignInDTO;
import com.lcy.projectscheduler.authorization.domain.UserService;
import com.lcy.projectscheduler.authorization.dto.UserModel;
import com.lcy.projectscheduler.authorization.dto.UserModelAssembler;
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
