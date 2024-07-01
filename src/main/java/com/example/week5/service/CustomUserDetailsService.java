package com.example.week5.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return User.withUsername("admin")
                    .password("{noop}adminpassword") // {noop} 表示不使用加密
                    .roles("ADMIN")
                    .build();
        } else if ("user".equals(username)) {
            return User.withUsername("user")
                    .password("{noop}password") // {noop} 表示不使用加密
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
