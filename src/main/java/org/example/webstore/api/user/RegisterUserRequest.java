package org.example.webstore.api.user;

public record RegisterUserRequest(
    String email,
    String username
) {}
