package org.example.webstore.controller;

import org.example.webstore.api.order.NewOrderItemRequest;
import org.example.webstore.api.order.OrderResponse;
import org.example.webstore.service.order.OrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new-order")
    public OrderResponse newOrder() {
        return orderService.newOrder();
    }

    @PostMapping("/add-item")
    public void addItem(@RequestBody NewOrderItemRequest request) { // TODO @Valid
        orderService.addOrderItem(request);
    }

}
