package com.lcy.ps.core.domain.user;

import com.lcy.ps.core.domain.base.BaseEntity;
import com.lcy.ps.core.dto.request.login.SignUpDTO;
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
