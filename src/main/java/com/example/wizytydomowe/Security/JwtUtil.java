package com.example.wizytydomowe.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JwtUtil {
    private static final long EXPIRATION_TIME = 900_000; // 15 minut
    private static final String SECRET = "TwojSekretnyKlucz"; // Użyj bezpiecznego klucza

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception){
            //Log or throw custom exception if token is invalid
            throw new RuntimeException("Token verification failed");
        }
    }
}
