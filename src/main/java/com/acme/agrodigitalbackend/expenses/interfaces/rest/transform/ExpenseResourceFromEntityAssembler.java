package com.acme.agrodigitalbackend.expenses.interfaces.rest.transform;

import com.acme.agrodigitalbackend.expenses.domain.model.aggregates.Expense;
import com.acme.agrodigitalbackend.expenses.interfaces.rest.resources.ExpenseResource;

public class ExpenseResourceFromEntityAssembler {
    public static ExpenseResource toResourceFromEntity(Expense entity) {
        return new ExpenseResource(
            entity.getId(),
            entity.getAmount(),
            entity.getDescription(),
            entity.getCategory(),
            entity.getDate(),
            entity.getType(),
            entity.getUserId(),
            entity.getUserName()
        );
    }
}