package org.example.webstore.controller;

import org.example.webstore.api.user.RegisterUserRequest;
import org.example.webstore.api.user.RegisterUserResponse;
import org.example.webstore.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }
}
