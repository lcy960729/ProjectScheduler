package com.lcy.ps.user.dto.request;

import lombok.Data;

@Data
public class SignInDTO {
    private String email;
    private String pw;
}