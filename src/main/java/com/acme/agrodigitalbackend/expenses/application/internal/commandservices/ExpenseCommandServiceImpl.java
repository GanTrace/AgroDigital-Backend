package com.acme.agrodigitalbackend.expenses.application.internal.commandservices;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import com.acme.agrodigitalbackend.expenses.domain.model.commands.CreateExpenseCommand;
import com.acme.agrodigitalbackend.expenses.domain.model.commands.DeleteExpenseCommand;
import com.acme.agrodigitalbackend.expenses.domain.services.ExpenseCommandService;
import com.acme.agrodigitalbackend.expenses.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {
    private final ExpenseRepository expenseRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Long handle(CreateExpenseCommand command) {
        var expense = new Expense(command);
        expenseRepository.save(expense);
        return expense.getId();
    }

    @Override
    public void handle(DeleteExpenseCommand command) {
        expenseRepository.deleteById(command.expenseId());
    }
}