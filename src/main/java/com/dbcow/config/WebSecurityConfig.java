package com.dbcow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/login")
        ).authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }
}