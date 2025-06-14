package com.acme.agrodigitalbackend.expenses.domain.model.queries;

public record GetExpenseByIdQuery(Long expenseId) {
    public GetExpenseByIdQuery {
        if (expenseId == null) {
            throw new IllegalArgumentException("Expense ID cannot be null");
        }
    }
}