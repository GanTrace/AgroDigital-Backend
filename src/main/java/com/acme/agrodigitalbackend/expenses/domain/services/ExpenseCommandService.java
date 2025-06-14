package com.acme.agrodigitalbackend.expenses.domain.services;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import com.acme.agrodigitalbackend.expenses.domain.model.commands.CreateExpenseCommand;
import com.acme.agrodigitalbackend.expenses.domain.model.commands.DeleteExpenseCommand;

public interface ExpenseCommandService {
    Long handle(CreateExpenseCommand command);
    void handle(DeleteExpenseCommand command);
}