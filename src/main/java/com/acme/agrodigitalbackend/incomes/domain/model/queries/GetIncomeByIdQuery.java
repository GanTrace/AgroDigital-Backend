package com.acme.agrodigitalbackend.incomes.domain.model.queries;

public record GetIncomeByIdQuery(Long incomeId) {
    public GetIncomeByIdQuery {
        if (incomeId == null) {
            throw new IllegalArgumentException("Income ID cannot be null");
        }
    }
}