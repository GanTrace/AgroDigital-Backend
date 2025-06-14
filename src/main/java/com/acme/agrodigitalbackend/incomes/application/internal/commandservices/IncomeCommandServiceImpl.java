package com.acme.agrodigitalbackend.incomes.application.internal.commandservices;

import com.acme.agrodigitalbackend.incomes.domain.model.aggregates.Income;
import com.acme.agrodigitalbackend.incomes.domain.model.commands.CreateIncomeCommand;
import com.acme.agrodigitalbackend.incomes.domain.services.IncomeCommandService;
import com.acme.agrodigitalbackend.incomes.infrastructure.persistence.jpa.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

@Service
public class IncomeCommandServiceImpl implements IncomeCommandService {
    private final IncomeRepository incomeRepository;

    public IncomeCommandServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Long handle(CreateIncomeCommand command) {
        var income = new Income(command);
        incomeRepository.save(income);
        return income.getId();
    }
}