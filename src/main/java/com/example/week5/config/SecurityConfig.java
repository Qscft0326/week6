package com.example.week5.config;

import com.example.week5.security.CustomAuthenticationProvider;
import com.example.week5.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/instances", "/actuator/**").disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/public/**").permitAll();
                auth.requestMatchers("/user/**").hasRole("USER");
                auth.requestMatchers("/admin/**").hasRole("ADMIN");
                auth.requestMatchers("/instances", "/actuator/**").permitAll();
                auth.anyRequest().authenticated();
            })
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .httpBasic(withDefaults -> {})
            .sessionManagement(sessionManagement -> 
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }
}
