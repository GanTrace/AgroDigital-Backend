package com.acme.agrodigitalbackend.expenses.domain.model.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateExpenseCommand(
    BigDecimal amount,
    String description,
    String category,
    LocalDateTime date,
    Long userId,
    String userName
) {
    public CreateExpenseCommand {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or blank");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("User name cannot be null or blank");
        }
    }
}