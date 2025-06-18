package com.acme.agrodigitalbackend.transactions.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResource(
    Long id,
    BigDecimal amount,
    String description,
    String category,
    LocalDateTime date,
    String type, // "income" or "expense"
    Long userId,
    String userName
) {
}