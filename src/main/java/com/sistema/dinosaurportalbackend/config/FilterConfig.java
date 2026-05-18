package com.sistema.dinosaurportalbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Registra el filtro JWT para que se ejecute en todas las rutas /api/*
@Configuration
public class FilterConfig {

    @Autowired private JwtFilter jwtFilter;

    // Aplica el filtro JWT a todas las peticiones que lleguen a /api/*
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration() {
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(jwtFilter);
        registration.addUrlPatterns("/api/*");
        registration.setOrder(1);
        return registration;
    }
}
