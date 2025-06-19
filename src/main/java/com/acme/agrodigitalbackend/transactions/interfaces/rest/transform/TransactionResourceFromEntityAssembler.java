package com.acme.agrodigitalbackend.transactions.interfaces.rest.transform;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import com.acme.agrodigitalbackend.incomes.domain.model.aggregates.Income;
import com.acme.agrodigitalbackend.transactions.interfaces.rest.resources.TransactionResource;

public class TransactionResourceFromEntityAssembler {
    
    public static TransactionResource toResourceFromIncomeEntity(Income income) {
        return new TransactionResource(
            income.getId(),
            income.getAmount(),
            income.getDescription(),
            income.getCategory(),
            income.getDate(),
            income.getType(),
            income.getUserId(),
            income.getUserName()
        );
    }
    
    public static TransactionResource toResourceFromExpenseEntity(Expense expense) {
        return new TransactionResource(
            expense.getId(),
            expense.getAmount(),
            expense.getDescription(),
            expense.getCategory(),
            expense.getDate(),
            expense.getType(),
            expense.getUserId(),
            expense.getUserName()
        );
    }
}