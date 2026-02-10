package org.example.webstore.controller;

import org.example.webstore.api.product.OrderResponse;
import org.example.webstore.service.order.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
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

}
