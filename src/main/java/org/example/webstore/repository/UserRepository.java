package org.example.webstore.repository;

import org.example.webstore.entity.User;

import java.util.Optional;

public interface UserRepository
        extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
