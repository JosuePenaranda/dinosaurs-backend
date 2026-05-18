package com.sistema.dinosaurportalbackend.config;

import com.sistema.dinosaurportalbackend.logic.servicios.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Filtro que intercepta cada petición y extrae el usuario del token JWT
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Lee el header Authorization de la petición
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            // Si el token es válido, pone el id, username y rol en el request
            if (jwtUtil.isValid(token)) {
                Claims claims = jwtUtil.getClaims(token);
                request.setAttribute("userId",   claims.get("id", Integer.class));
                request.setAttribute("username", claims.getSubject());
                request.setAttribute("rol",      claims.get("rol", String.class));
            }
        }

        filterChain.doFilter(request, response);
    }
}
