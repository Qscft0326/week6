package com.example.week5;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class Week5Application {

    public static void main(String[] args) {
        SpringApplication.run(Week5Application.class, args);
    }
}
