package com.lcy.ps.integrate.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MemberModel implements Serializable {
    private String email;

    public MemberModel(String email) {
        this.email = email;
    }

    public static MemberModel of(String email) {
        return new MemberModel(email);
    }

    public static MemberModel of(UserModel userModel) {
        return new MemberModel(userModel.getEmail());
    }
}
