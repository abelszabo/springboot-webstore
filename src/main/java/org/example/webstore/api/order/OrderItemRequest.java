package org.example.webstore.api.order;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        String orderNumber, // FIXME NOT NULL
        Long productId, // FIXME NOT NULL

//        @NotNull(message = "Quantity is required")
//        @Positive(message = "Quantity must be positive")
        Integer quantity
) {}