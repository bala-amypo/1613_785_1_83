package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component   // ⭐ THIS IS THE FIX
public class JwtTokenProvider {

    private final String SECRET_KEY = "secret-key";
    private final long VALIDITY = 1000 * 60 * 60; // 1 hour

    public String createToken(String email, String role, Long userId) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        claims.put("userId", userId);

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
