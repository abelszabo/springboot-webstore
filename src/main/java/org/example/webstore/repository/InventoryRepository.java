package org.example.webstore.repository;

import org.example.webstore.entity.Inventory;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface InventoryRepository
        extends BaseRepository<Inventory, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select i
        from Inventory i
        where i.product.id = :productId
    """)
    Optional<Inventory> lockByProductId(Long productId);

    Optional<Inventory> findByProductId(Long productId);
}
