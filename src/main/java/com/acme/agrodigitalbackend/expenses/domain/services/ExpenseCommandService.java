package com.acme.agrodigitalbackend.expenses.domain.services;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import com.acme.agrodigitalbackend.expenses.domain.model.commands.CreateExpenseCommand;

public interface ExpenseCommandService {
    Long handle(CreateExpenseCommand command);
}