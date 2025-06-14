package com.acme.agrodigitalbackend.incomes.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record IncomeResource(
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