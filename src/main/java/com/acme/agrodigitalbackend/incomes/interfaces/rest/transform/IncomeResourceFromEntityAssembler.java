package com.acme.agrodigitalbackend.incomes.interfaces.rest.transform;

import com.acme.agrodigitalbackend.incomes.domain.model.aggregates.Income;
import com.acme.agrodigitalbackend.incomes.interfaces.rest.resources.IncomeResource;

public class IncomeResourceFromEntityAssembler {
    public static IncomeResource toResourceFromEntity(Income entity) {
        return new IncomeResource(
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