package org.example.webstore.service.inventory;

import org.example.webstore.entity.Inventory;
import org.example.webstore.entity.OrderHead;
import org.example.webstore.entity.OrderItem;
import org.example.webstore.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void reserve(OrderHead order) {

//        for (OrderItem item : order.getItems()) {
//            Inventory inv =
//                    inventoryRepository.findByProductForUpdate(
//                            item.getProduct());
//
//            inv.reserve(item.getQuantity());
//        }
    }

    @Transactional
    public void finalizeReservationForPaidOrder(OrderHead order) {

//        for (OrderItem item : order.getItems()) {
//            Inventory inv =
//                    inventoryRepository.findByProductForUpdate(
//                            item.getProduct());
//
//            inv.finalizeReservation(item.getQuantity());
//        }
    }
}
