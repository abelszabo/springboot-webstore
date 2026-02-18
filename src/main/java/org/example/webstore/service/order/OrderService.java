package org.example.webstore.service.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.webstore.api.order.OrderItemRequest;
import org.example.webstore.api.order.OrderItemResponse;
import org.example.webstore.api.order.OrderResponse;
import org.example.webstore.entity.OrderHead;
import org.example.webstore.entity.OrderItem;
import org.example.webstore.entity.enums.OrderStatus;
import org.example.webstore.exception.ItemNotFoundException;
import org.example.webstore.repository.OrderHeadRepository;
import org.example.webstore.repository.OrderItemRepository;
import org.example.webstore.repository.ProductRepository;
import org.example.webstore.service.inventory.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderHeadRepository orderHeadRepository;
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;
    private final OrderItemRepository orderItemRepository;

    @PersistenceContext
    private EntityManager em;

    public OrderService(OrderHeadRepository orderHeadRepository,
                        ProductRepository productRepository,
                        InventoryService inventoryService, OrderItemRepository orderItemRepository) {
        this.orderHeadRepository = orderHeadRepository;
        this.productRepository = productRepository;
        this.inventoryService = inventoryService;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderResponse newOrder() {
        //return this.orderHeadRepository.newOrder();

        String orderNumber = "ORDER-" + Math.abs(UUID.randomUUID().hashCode());
        //orderNumber = "ORDER-12345";

        OrderHead orderHead = new OrderHead();
        orderHead.newOrder();
        orderHead.setOrderNumber(orderNumber);

        orderHeadRepository.save(orderHead);

        return new OrderResponse(orderNumber);
    }

    @Transactional
    public OrderItemResponse addOrderItem(OrderItemRequest request) {
        System.out.println(request);

        var orderHead = orderHeadRepository.findByOrderNumber(request.orderNumber())
                .orElseThrow(() -> new ItemNotFoundException("Order not found"));

        var product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ItemNotFoundException("Product not found"));

        if (null == request.quantity() || request.quantity() < 1) { // TODO @Valid
            throw new IllegalArgumentException("Quantity should be greater than zero");
        }

        //Long productId = product.getId();

        //var orderItemOpt = orderItemRepository.findByOrderAndProductForUpdate(orderHead.getId(), productId);
        var orderItemOpt = orderItemRepository.findByOrderAndProductForUpdate(orderHead, product);
        //var orderItemOpt = orderItemRepository.findByOrderAndProductForUpdateNative(orderHead.getId(), productId);
        OrderItem orderItem;
        int quantity = request.quantity();

        if (orderItemOpt.isPresent()) {
            orderItem = orderItemOpt.get();
            quantity += orderItem.getQuantity();
        } else {
            orderItem = new OrderItem();
            orderItem.setOrderHead(orderHead);
            orderItem.setProduct(product);
            orderItem.setProductName(product.getName());
            orderItem.setUnitPrice(product.getPrice());
        }

        orderItem.setQuantity(quantity);
        orderItemRepository.save(orderItem);

        String status = "OK"; // TODO
        return new OrderItemResponse(product.getId(), status, quantity);
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
