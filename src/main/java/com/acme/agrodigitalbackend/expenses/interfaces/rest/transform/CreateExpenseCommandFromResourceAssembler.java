package com.acme.agrodigitalbackend.expenses.interfaces.rest.transform;

import com.acme.agrodigitalbackend.expenses.domain.model.commands.CreateExpenseCommand;
import com.acme.agrodigitalbackend.expenses.interfaces.rest.resources.CreateExpenseResource;

public class CreateExpenseCommandFromResourceAssembler {
    public static CreateExpenseCommand toCommandFromResource(CreateExpenseResource resource) {
        return new CreateExpenseCommand(
            resource.amount(),
            resource.description(),
            resource.category(),
            resource.date(),
            resource.userId(),
            resource.userName()
        );
    }
}