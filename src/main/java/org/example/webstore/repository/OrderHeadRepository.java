package org.example.webstore.repository;

import jakarta.persistence.LockModeType;
import org.example.webstore.entity.OrderHead;
import org.example.webstore.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderHeadRepository
        extends BaseRepository<OrderHead, Long>, OrderHeadRepositoryCustom {

    Optional<OrderHead> findByOrderNumber(String orderNumber);

    List<OrderHead> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<OrderHead> findByStatusAndUpdatedAtBefore(
            OrderStatus status,
            Instant before
    );


    Optional<OrderHead> findByUuid(UUID uuid);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select o from OrderHead o where o.uuid = :uuid")
    Optional<OrderHead> findByUuidForUpdate(UUID uuid);
}
