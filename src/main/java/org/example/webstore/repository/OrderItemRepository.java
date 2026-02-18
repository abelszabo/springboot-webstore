package org.example.webstore.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.example.webstore.entity.OrderHead;
import org.example.webstore.entity.OrderItem;
import org.example.webstore.entity.Product;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Objects;
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

    default Optional<OrderItem> findByOrderAndProductForUpdate(
            OrderHead orderHead,
            Product product
    ) {
        Objects.requireNonNull(orderHead, "orderHead must not be null");
        Objects.requireNonNull(product, "product must not be null");

        return findByOrderAndProductForUpdate(
                orderHead.getId(),
                product.getId()
        );
    }

    @Query(value = """
            SELECT * FROM order_item
            WHERE order_head_id = :orderHeadId 
                AND product_id = :productId
            FOR UPDATE
            """, nativeQuery = true)
    Optional<OrderItem> findByOrderAndProductForUpdateNative(Long orderHeadId, Long productId);
}
