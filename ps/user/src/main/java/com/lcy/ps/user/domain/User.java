package com.lcy.ps.user.domain;

import com.lcy.ps.core.domain.BaseEntity;
import com.lcy.ps.user.dto.request.SignUpDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {
    @Column
    private String email;

    @Column
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
