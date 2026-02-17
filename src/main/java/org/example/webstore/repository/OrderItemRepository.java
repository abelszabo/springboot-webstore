package org.example.webstore.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.example.webstore.entity.OrderItem;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

public interface OrderItemRepository
        extends BaseRepository<OrderItem, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "500") //5000")
    })
    @Query("""
            SELECT o FROM OrderItem o 
            WHERE o.orderHead.id = :orderHeadId 
                AND o.product.id = :productId
            """)
    Optional<OrderItem> findByOrderAndProductForUpdate(Long orderHeadId, Long productId);

    @Query(value = """
            SELECT * FROM order_item
            WHERE order_head_id = :orderHeadId 
                AND product_id = :productId
            FOR UPDATE
            """, nativeQuery = true)
    Optional<OrderItem> findByOrderAndProductForUpdateNative(Long orderHeadId, Long productId);
}
