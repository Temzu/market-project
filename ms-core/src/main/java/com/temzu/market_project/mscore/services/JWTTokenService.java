package com.temzu.market_project.mscore.services;

import com.temzu.market_project.mscore.interfaces.ITokenService;
import com.temzu.market_project.mscore.model.UserInfo;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JWTTokenService implements ITokenService {

    private final String JWT_SECRET = "3b2648762a13d3f6be076edb7f70fa391e83049e1eaef30448eecc4effd31e74f7eaa092196868d677986ab5f12afd579a894d0daa0716da1d72c443a539976e";

    @Override
    public String generateToken(UserInfo user) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        String compactTokenString = Jwts.builder()
                .claim("id", user.getUserId())
                .claim("sub", user.getUserEmail())
                .claim("role", user.getRole())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

        return "Bearer " + compactTokenString;
    }

    @Override
    public UserInfo parseToken(String token) throws ExpiredJwtException {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);

        String email = jwsClaims.getBody()
                .getSubject();

        Long userId = jwsClaims.getBody()
                .get("id", Long.class);

        String role = jwsClaims.getBody()
                .get("role", String.class);

        return UserInfo.builder()
                .userId(userId)
                .userEmail(email)
                .role(role)
                .build();
    }
}
