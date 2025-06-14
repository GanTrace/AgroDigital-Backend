package com.acme.agrodigitalbackend.expenses.domain.services;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetAllExpensesQuery;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpenseByIdQuery;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpensesByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface ExpenseQueryService {
    List<Expense> handle(GetAllExpensesQuery query);
    Optional<Expense> handle(GetExpenseByIdQuery query);
    List<Expense> handle(GetExpensesByUserIdQuery query);
}