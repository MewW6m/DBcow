package com.dbcow.config;

import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("routes");
        System.out.println("=====================================================================================");
        http
                .formLogin(form -> form
                        .loginProcessingUrl(rb.getString("common.sc.login"))
                        .defaultSuccessUrl(rb.getString("table.sc.db"))
                        .failureUrl(rb.getString("common.sc.login") + "?errorMsg")
                        .loginPage(rb.getString("common.sc.login"))
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher(rb.getString("common.sc.logout")))
                        .logoutUrl(rb.getString("common.sc.logout"))
                        .logoutSuccessUrl(rb.getString("common.sc.login"))
                        .invalidateHttpSession(true) // ログアウトしたらセッションを無効にする
                        .deleteCookies("JSESSIONID") // ログアウトしたら cookieの JSESSIONID を削除
                ).authorizeHttpRequests(authz -> authz
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers(rb.getString("user.sc.regist")).permitAll()
                        .requestMatchers(HttpMethod.POST, rb.getString("user.api.detail")).permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}