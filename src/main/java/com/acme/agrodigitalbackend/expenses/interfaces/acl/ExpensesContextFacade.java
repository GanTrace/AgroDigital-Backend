package com.acme.agrodigitalbackend.expenses.interfaces.acl;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpenseByIdQuery;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpensesByUserIdQuery;
import com.acme.agrodigitalbackend.expenses.domain.services.ExpenseQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpensesContextFacade {
    private final ExpenseQueryService expenseQueryService;

    public ExpensesContextFacade(ExpenseQueryService expenseQueryService) {
        this.expenseQueryService = expenseQueryService;
    }

    public Optional<Expense> fetchExpenseById(Long expenseId) {
        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        return expenseQueryService.handle(getExpenseByIdQuery);
    }

    public List<Expense> fetchExpensesByUserId(Long userId) {
        var getExpensesByUserIdQuery = new GetExpensesByUserIdQuery(userId);
        return expenseQueryService.handle(getExpensesByUserIdQuery);
    }
}