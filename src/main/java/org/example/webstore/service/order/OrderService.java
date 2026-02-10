package org.example.webstore.service.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.webstore.api.product.OrderResponse;
import org.example.webstore.entity.OrderHead;
import org.example.webstore.entity.enums.OrderStatus;
import org.example.webstore.repository.OrderHeadRepository;
import org.example.webstore.service.inventory.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderHeadRepository orderHeadRepository;
    private final InventoryService inventoryService;

    @PersistenceContext
    private EntityManager em;

    public OrderService(OrderHeadRepository orderHeadRepository,
                        InventoryService inventoryService) {
        this.orderHeadRepository = orderHeadRepository;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public OrderResponse newOrder() {
        //return this.orderHeadRepository.newOrder();

        String orderNumber = "ORDER-" + Math.abs(UUID.randomUUID().hashCode());

        OrderHead orderHead = new OrderHead();
        orderHead.newOrder();
        orderHead.setOrderNumber(orderNumber);

        this.orderHeadRepository.save(orderHead);

        return new OrderResponse(orderNumber);
    }

    @Transactional
    public void finalizeOrder(UUID orderUuid) {

        OrderHead order = loadForFinalize(orderUuid);
        assertManaged(order);

        if (order.getStatus() == OrderStatus.PAID) {
            return;
        }

        order.markPaid();

        orderHeadRepository.flush();

        inventoryService.finalizeReservationForPaidOrder(order);
    }

    private OrderHead loadForFinalize(UUID orderUuid) {
        return orderHeadRepository.findByUuidForUpdate(orderUuid)
                .orElseThrow(() ->
                        new IllegalStateException("Order not found"));
    }

    private void assertManaged(Object entity) {
        if (!em.contains(entity)) {
            throw new IllegalStateException(
                    "Detached entity detected in OrderService"
            );
        }
    }
}
