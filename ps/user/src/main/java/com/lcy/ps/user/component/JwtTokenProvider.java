package com.lcy.ps.user.component;

import com.lcy.ps.core.exception.ExpiredTokenException;
import com.lcy.ps.core.exception.InvalidTokenException;
import com.lcy.ps.user.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

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

            if (expiredTime.before(new Date())) {
                throw new ExpiredTokenException();
            }

            return Long.parseLong(claims.getBody().getSubject());

        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
