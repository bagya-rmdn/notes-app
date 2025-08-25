package com.example.notes_app.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import com.example.notes_app.config.JwtConfiguration;
import com.example.notes_app.entity.UserEntity;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Utility class for generating, parsing, and validating JWT (JSON Web Token) tokens.
 * Provides helper methods for working with user authentication tokens.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {
    private final JwtConfiguration jwtConfiguration;

    public String generateToken(UserEntity user) {
        return createToken(Map.of("type", "Bearer", "username",
                user.getUsername(), "id", user.getId()), user);
    }

    private String createToken(Map<String, Object> claims, UserEntity user) {
        return Jwts.builder().claims().add(claims).subject(user.getUsername())
                .issuer("https://abc.xyz/auth").issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .and()
                .signWith(getSecretKey()).compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public void validateToken(String token) {
        this.extractUsername(token);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfiguration.getSecret()));
        return key;
    }

    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("id", Integer.class)).longValue();
    }
}
