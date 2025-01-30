package com.devDaph.todo.list.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "your_secret_key";  // Replace with your secret key

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Set expiration time (e.g., 10 hours)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
