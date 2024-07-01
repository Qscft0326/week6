package com.example.week5.controller;

import com.example.week5.util.JwtUtils;
import com.example.week5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public Map<String, String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final String token = jwtUtils.generateToken(authenticationRequest.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome to home!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome to admin!";
    }
}
