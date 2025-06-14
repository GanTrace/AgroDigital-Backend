package com.acme.agrodigitalbackend.incomes.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateIncomeResource(
    BigDecimal amount,
    String description,
    String category,
    LocalDateTime date,
    Long userId,
    String userName
) {
}