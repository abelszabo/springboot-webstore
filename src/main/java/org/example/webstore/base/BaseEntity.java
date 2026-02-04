package org.example.webstore.base;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @Version
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Long version;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Instant createdAt;

    @Setter(AccessLevel.NONE)
    private Instant updatedAt;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID createdBy;

    @Setter(AccessLevel.NONE)
    private UUID updatedBy;

    @PrePersist
    protected void onCreate() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }

        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;

        UUID actor = RequestContextHolder.getUserUuidOrSystem();

        createdBy = actor;
        updatedBy = actor;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();

        UUID actor = RequestContextHolder.getUserUuid();
        if (actor == null) {
            actor = SystemUsers.SYSTEM_UUID;
        }

        updatedBy = actor;
    }

    // --- getters ---

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getVersion() {
        return version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }
}
