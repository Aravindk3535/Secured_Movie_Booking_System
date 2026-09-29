package com.ticket.bookingApplication.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String userName) {
        return JWT.create()
                .withSubject(userName)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() +  expiration))
                .sign(Algorithm.HMAC256(secret));
    }

    public String extractUserName(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);
        return decodedJWT.getSubject();
    }

    public boolean isTokenValid(String token, String userName) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);

        return decodedJWT.getSubject().equals(userName)
                && decodedJWT.getExpiresAt().after(new Date());
    }
}
