package com.lcy.ps.auth.api.v1.domain.user;

import com.lcy.ps.core.dto.request.login.SignInDTO;
import org.springframework.stereotype.Service;


public interface AuthService {
    long auth(String token);
}
