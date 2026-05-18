package com.sistema.dinosaurportalbackend.logic.servicios;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

// Utilidad para generar y validar tokens JWT
@Component
public class JwtUtil {

    // Clave secreta para firmar el token
    private static final String SECRET = "dinosaur-portal-secret-key-must-be-long-enough-256bits";

    // Tiempo de expiración del token: 24 horas
    private static final long EXPIRATION_MS = 86400000;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Genera un token con el id, username y rol del usuario
    public String generateToken(Integer id, String username, String rol) {
        return Jwts.builder()
                .subject(username)
                .claim("id", id)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key)
                .compact();
    }

    // Extrae los datos del token
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Verifica si el token es válido y no está expirado
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
