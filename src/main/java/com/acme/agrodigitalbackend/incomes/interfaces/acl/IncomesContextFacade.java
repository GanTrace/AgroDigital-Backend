package com.acme.agrodigitalbackend.incomes.interfaces.acl;

import com.acme.agrodigitalbackend.incomes.domain.model.aggregates.Income;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomeByIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomesByUserIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.services.IncomeQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomesContextFacade {
    private final IncomeQueryService incomeQueryService;

    public IncomesContextFacade(IncomeQueryService incomeQueryService) {
        this.incomeQueryService = incomeQueryService;
    }

    public Optional<Income> fetchIncomeById(Long incomeId) {
        var getIncomeByIdQuery = new GetIncomeByIdQuery(incomeId);
        return incomeQueryService.handle(getIncomeByIdQuery);
    }

    public List<Income> fetchIncomesByUserId(Long userId) {
        var getIncomesByUserIdQuery = new GetIncomesByUserIdQuery(userId);
        return incomeQueryService.handle(getIncomesByUserIdQuery);
    }
}