package org.example.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T, ID>
        extends JpaRepository<T, ID> {

    Optional<T> findByUuid(UUID uuid);
}
