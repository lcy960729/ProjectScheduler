package com.lcy.projectscheduler.security.domain;

import com.lcy.projectscheduler.api.v1.domain.base.BaseEntity;
import com.lcy.projectscheduler.api.v1.dto.request.login.SignUpDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {
    private String email;
    private String pw;

    @Builder
    public User(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }

    public static User of(SignUpDTO signUpDTO){
        return new User(signUpDTO.getEmail(), signUpDTO.getPw());
    }
}
