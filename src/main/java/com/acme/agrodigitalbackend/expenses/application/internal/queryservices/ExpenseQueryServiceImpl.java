package com.acme.agrodigitalbackend.expenses.application.internal.queryservices;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetAllExpensesQuery;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpenseByIdQuery;
import com.acme.agrodigitalbackend.expenses.domain.model.queries.GetExpensesByUserIdQuery;
import com.acme.agrodigitalbackend.expenses.domain.services.ExpenseQueryService;
import com.acme.agrodigitalbackend.expenses.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseQueryServiceImpl implements ExpenseQueryService {
    private final ExpenseRepository expenseRepository;

    public ExpenseQueryServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> handle(GetAllExpensesQuery query) {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> handle(GetExpenseByIdQuery query) {
        return expenseRepository.findById(query.expenseId());
    }

    @Override
    public List<Expense> handle(GetExpensesByUserIdQuery query) {
        return expenseRepository.findByUserId(query.userId());
    }
}