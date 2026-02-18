package org.example.webstore.controller;

import org.example.webstore.api.order.OrderItemRequest;
import org.example.webstore.api.order.OrderItemResponse;
import org.example.webstore.api.order.OrderResponse;
import org.example.webstore.service.order.OrderService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<OrderResponse> newOrder() {
        //return ResponseEntity.ok().body(orderService.newOrder());
        return ResponseEntity.ok(orderService.newOrder());
    }

    @PostMapping("/item")
    public ResponseEntity<OrderItemResponse> addOrderItem(@RequestBody OrderItemRequest request) { // TODO @Valid
        //orderService.addOrderItem(request);
        //return ResponseEntity.noContent().build();
        return ResponseEntity.ok(orderService.addOrderItem(request));
    }

}
