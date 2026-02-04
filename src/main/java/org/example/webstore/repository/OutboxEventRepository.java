package org.example.webstore.repository;

import org.example.webstore.entity.OutboxEvent;
import org.example.webstore.entity.enums.OutboxStatus;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import java.util.List;

public interface OutboxEventRepository
        extends BaseRepository<OutboxEvent, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select e
        from OutboxEvent e
        where e.status = :status
        order by e.createdAt
    """)
    List<OutboxEvent> lockNextBatch(
            OutboxStatus status,
            Pageable pageable
    );
}
