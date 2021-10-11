package com.lcy.ps.main.api.v1.domain.user;


public interface AuthService {
    long auth(String token);
}
