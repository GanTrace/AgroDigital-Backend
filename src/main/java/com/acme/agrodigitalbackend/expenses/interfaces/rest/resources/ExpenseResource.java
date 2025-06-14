package com.acme.agrodigitalbackend.expenses.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseResource(
    Long id,
    BigDecimal amount,
    String description,
    String category,
    LocalDateTime date,
    String type,
    Long userId,
    String userName
) {
}