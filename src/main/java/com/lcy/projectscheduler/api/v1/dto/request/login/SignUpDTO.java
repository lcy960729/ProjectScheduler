package com.lcy.projectscheduler.api.v1.dto.request.login;

import lombok.Data;

@Data
public class SignUpDTO {
    private String email;
    private String pw;
}
