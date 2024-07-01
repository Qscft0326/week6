package com.example.week5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "User Content.";
    }

    @PostMapping("/admin/postArticle")
    @PreAuthorize("hasRole('ADMIN')")
    public String postArticle() {
        return "Article Posted.";
    }

    @PostMapping("/admin/postAd")
    @PreAuthorize("hasRole('ADMIN')")
    public String postAd() {
        return "Ad Posted.";
    }
}
