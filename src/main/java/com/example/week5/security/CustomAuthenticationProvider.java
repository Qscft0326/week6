package com.example.week5.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 自定义认证逻辑
        if ("user".equals(username) && "password".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, authentication.getAuthorities());
        } else if ("admin".equals(username) && "adminpassword".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, authentication.getAuthorities());
        } else {
            throw new AuthenticationException("Invalid credentials") {};
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
