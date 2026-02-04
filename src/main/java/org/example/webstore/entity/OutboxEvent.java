package org.example.webstore.entity;

import org.example.webstore.base.BaseEntity;
import jakarta.persistence.*;
import org.example.webstore.entity.enums.OutboxStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "outbox_event",
        indexes = @Index(name = "ux_outbox_event_id", columnList = "eventId", unique = true)
)
public class OutboxEvent extends BaseEntity {

    @Column(nullable = false, unique = true)
    private UUID eventId;

    @Column(nullable = false)
    private String aggregateType;

    @Column(nullable = false)
    private UUID aggregateUuid;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false, columnDefinition = "jsonb")
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OutboxStatus status;

    private int retryCount;
    private Instant sentAt;

    @PrePersist
    void init() {
        if (eventId == null) {
            eventId = UUID.randomUUID();
        }
        if (status == null) {
            status = OutboxStatus.NEW;
        }
    }

    // getters & setters
}
