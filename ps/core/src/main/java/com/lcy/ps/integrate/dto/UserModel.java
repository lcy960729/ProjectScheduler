package com.lcy.ps.integrate.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserModel implements Serializable {
    private String email;

    public UserModel(String email) {
        this.email = email;
    }

    public static UserModel of(String email) {
        return new UserModel(email);
    }


}
