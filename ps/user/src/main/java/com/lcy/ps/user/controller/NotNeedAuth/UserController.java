package com.lcy.ps.user.controller.NotNeedAuth;

import com.lcy.ps.user.domain.User;
import com.lcy.ps.user.dto.request.SignInDTO;
import com.lcy.ps.user.dto.request.SignUpDTO;
import com.lcy.ps.user.mapper.UserModelAssembler;
import com.lcy.ps.user.domain.UserService;
import com.lcy.ps.user.dto.response.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

    @PostMapping(path = "signUp")
    public ResponseEntity<UserModel> signUp(@RequestBody SignUpDTO signUpDTO) {
        User user = userService.signUp(signUpDTO);
        UserModel userModel = userModelAssembler.toModel(user);

        return ResponseEntity.ok(userModel);
    }
}
