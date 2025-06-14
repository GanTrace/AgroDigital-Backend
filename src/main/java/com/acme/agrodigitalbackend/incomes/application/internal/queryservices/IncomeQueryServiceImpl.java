package com.acme.agrodigitalbackend.incomes.application.internal.queryservices;

import com.acme.agrodigitalbackend.incomes.domain.model.aggregates.Income;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetAllIncomesQuery;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomeByIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.model.queries.GetIncomesByUserIdQuery;
import com.acme.agrodigitalbackend.incomes.domain.services.IncomeQueryService;
import com.acme.agrodigitalbackend.incomes.infrastructure.persistence.jpa.repositories.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeQueryServiceImpl implements IncomeQueryService {
    private final IncomeRepository incomeRepository;

    public IncomeQueryServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<Income> handle(GetAllIncomesQuery query) {
        return incomeRepository.findAll();
    }

    @Override
    public Optional<Income> handle(GetIncomeByIdQuery query) {
        return incomeRepository.findById(query.incomeId());
    }

    @Override
    public List<Income> handle(GetIncomesByUserIdQuery query) {
        return incomeRepository.findByUserId(query.userId());
    }
}