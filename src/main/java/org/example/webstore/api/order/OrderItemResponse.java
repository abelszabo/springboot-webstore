package org.example.webstore.api.order;

public record OrderItemResponse(
    Long productId,
    String status, // TODO
    Integer quantity
) {}
