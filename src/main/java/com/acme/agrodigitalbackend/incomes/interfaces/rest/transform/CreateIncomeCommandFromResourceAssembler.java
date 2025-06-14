package com.acme.agrodigitalbackend.incomes.interfaces.rest.transform;

import com.acme.agrodigitalbackend.incomes.domain.model.commands.CreateIncomeCommand;
import com.acme.agrodigitalbackend.incomes.interfaces.rest.resources.CreateIncomeResource;

public class CreateIncomeCommandFromResourceAssembler {
    public static CreateIncomeCommand toCommandFromResource(CreateIncomeResource resource) {
        return new CreateIncomeCommand(
            resource.amount(),
            resource.description(),
            resource.category(),
            resource.date(),
            resource.userId(),
            resource.userName()
        );
    }
}