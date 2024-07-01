package com.example.week5.controller;

import com.example.week5.model.AuthRequest;
import com.example.week5.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            return jwtUtils.generateToken(authRequest.getUsername());
        } catch (Exception e) {
            throw new Exception("Invalid credentials", e);
        }
    }

    @GetMapping("/test")
    public String test() {
        return "GET request is working!";
    }
}
