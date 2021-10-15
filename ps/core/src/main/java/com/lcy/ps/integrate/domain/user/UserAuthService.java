package com.lcy.ps.integrate.domain.user;

public interface UserAuthService {
    long auth(String token);
}
