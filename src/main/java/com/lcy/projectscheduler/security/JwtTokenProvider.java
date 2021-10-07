package com.lcy.projectscheduler.security;

import com.lcy.projectscheduler.exception.AuthenticationException;
import com.lcy.projectscheduler.exception.InvalidTokenException;
import com.lcy.projectscheduler.exceptionHandler.dto.ExceptionModel;
import com.lcy.projectscheduler.security.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(user.getId()));
        claims.put("userEmail", user.getEmail());
        claims.put("role", "USER");

        Date now = new Date();
        Date expiredTime = new Date(now.getTime() + (30 * 60 * 1000L));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredTime)
                .signWith(secretKey)
                .compact();
    }

    public long validateTokenAndReturnUserId(String token) throws InvalidTokenException {
        try {
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();


            Jws<Claims> claims = jwtParser.parseClaimsJws(token);

            Date expiredTime = claims.getBody().getExpiration();

            if (!expiredTime.before(new Date())) {
                return Long.parseLong(claims.getBody().getSubject());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new InvalidTokenException();
    }
}
