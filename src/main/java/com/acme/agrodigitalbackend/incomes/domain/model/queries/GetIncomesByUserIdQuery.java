package com.acme.agrodigitalbackend.incomes.domain.model.queries;

public record GetIncomesByUserIdQuery(Long userId) {
    public GetIncomesByUserIdQuery {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
    }
}