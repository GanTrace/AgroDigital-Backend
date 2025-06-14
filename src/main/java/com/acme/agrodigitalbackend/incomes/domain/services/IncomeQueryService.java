package com.acme.agrodigitalbackend.incomes.domain.services;

import com.acme.agrodigitalbackend.incomes.domain.model.aggregates.Income;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetAllIncomesQuery;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomeByIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomesByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface IncomeQueryService {
    List<Income> handle(GetAllIncomesQuery query);
    Optional<Income> handle(GetIncomeByIdQuery query);
    List<Income> handle(GetIncomesByUserIdQuery query);
}