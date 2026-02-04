package org.example.webstore.service.order;

import org.example.webstore.entity.OrderHead;
import org.example.webstore.entity.enums.OrderStatus;
import org.example.webstore.repository.OrderHeadRepository;
import org.example.webstore.service.inventory.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CheckoutConfirmService {

    private final OrderHeadRepository orderHeadRepository;
    private final InventoryService inventoryService;

    public CheckoutConfirmService(OrderHeadRepository orderHeadRepository,
                                  InventoryService inventoryService) {
        this.orderHeadRepository = orderHeadRepository;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public void confirmCheckout(UUID orderUuid) {

        OrderHead order =
                orderHeadRepository.findByUuidForUpdate(orderUuid)
                        .orElseThrow(() ->
                                new IllegalStateException("Order not found"));

        if (order.getStatus() != OrderStatus.DRAFT) {
            throw new IllegalStateException(
                    "Order already confirmed");
        }

        inventoryService.reserve(order);

        order.startPayment();
        orderHeadRepository.save(order);
    }
}
