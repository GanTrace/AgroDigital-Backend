package com.acme.agrodigitalbackend.expenses.domain.model.commands;

public record DeleteExpenseCommand(
        Long expenseId
) {
}