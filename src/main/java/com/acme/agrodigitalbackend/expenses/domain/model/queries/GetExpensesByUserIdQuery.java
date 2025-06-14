package com.acme.agrodigitalbackend.expenses.domain.model.queries;

public record GetExpensesByUserIdQuery(Long userId) {
    public GetExpensesByUserIdQuery {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
    }
}