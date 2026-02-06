package org.example.webstore.api.product;

import java.math.BigDecimal;

public record ProductResponse(
    Long id,
    String name,
    String description,
    BigDecimal price,
    String categoryName
) {}
