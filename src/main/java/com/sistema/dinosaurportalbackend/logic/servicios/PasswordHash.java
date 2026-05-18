package com.sistema.dinosaurportalbackend.logic.servicios;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

// Maneja el encriptado y verificación de contraseñas con BCrypt
@Component
public class PasswordHash {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Encripta una contraseña en texto plano
    public String hash(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    // Verifica si una contraseña en texto plano coincide con la encriptada
    public boolean verify(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
