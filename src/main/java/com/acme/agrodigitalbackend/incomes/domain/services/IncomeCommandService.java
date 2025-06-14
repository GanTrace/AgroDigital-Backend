package com.acme.agrodigitalbackend.incomes.domain.services;

import com.acme.agrodigitalbackend.incomes.domain.model.aggregates.Income;
import com.acme.agrodigitalbackend.incomes.domain.model.commands.CreateIncomeCommand;
import com.acme.agrodigitalbackend.incomes.domain.model.commands.DeleteIncomeCommand;

public interface IncomeCommandService {
    Long handle(CreateIncomeCommand command);
    void handle(DeleteIncomeCommand command);
}