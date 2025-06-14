package com.acme.agrodigitalbackend.expenses.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateExpenseResource(
    BigDecimal amount,
    String description,
    String category,
    LocalDateTime date,
    Long userId,
    String userName
) {
}