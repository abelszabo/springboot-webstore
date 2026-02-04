package org.example.webstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Base path for all endpoints in this class
public class MyController {

    @GetMapping("/hello") // Specific path for this endpoint
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}