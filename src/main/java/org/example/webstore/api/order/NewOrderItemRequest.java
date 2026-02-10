package org.example.webstore.api.order;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Positive;

public record NewOrderItemRequest (
        String orderNumber,
        Long productId,

//        @NotNull(message = "Quantity is required")
//        @Positive(message = "Quantity must be positive")
        Integer quantity
) {}