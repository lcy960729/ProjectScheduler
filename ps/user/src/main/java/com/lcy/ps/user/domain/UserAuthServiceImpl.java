package com.lcy.ps.user.domain;

import com.lcy.ps.integrate.domain.user.UserAuthService;
import com.lcy.ps.user.component.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public long auth(String token) {
        return jwtTokenProvider.validateTokenAndReturnUserId(token);
    }
}
