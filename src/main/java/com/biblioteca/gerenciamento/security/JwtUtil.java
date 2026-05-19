package com.biblioteca.gerenciamento.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {

    private final String secretKey =
            "sua-chave-secreta-super-segura-que-deve-ser-bem-longa";

    // access token
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())

                // 15 minutos
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000L * 60 * 15
                        )
                )

                .signWith(
                        Keys.hmacShaKeyFor(
                                secretKey.getBytes(StandardCharsets.UTF_8)
                        ),
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    // refresh token
    public String generateRefreshToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())

                // 7 dias
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000L * 60 * 60 * 24 * 7
                        )
                )

                .signWith(
                        Keys.hmacShaKeyFor(
                                secretKey.getBytes(StandardCharsets.UTF_8)
                        ),
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    public Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                secretKey.getBytes(StandardCharsets.UTF_8)
                        )
                )

                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extrairEmailToken(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {

        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public boolean validateToken(
            String token,
            String username
    ) {

        final String extractedUsername =
                extrairEmailToken(token);

        return (
                extractedUsername.equals(username)
                        &&
                        !isTokenExpired(token)
        );
    }
}