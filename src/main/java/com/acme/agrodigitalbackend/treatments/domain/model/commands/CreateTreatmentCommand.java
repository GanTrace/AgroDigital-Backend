package com.acme.agrodigitalbackend.treatments.domain.model.commands;

import com.acme.agrodigitalbackend.treatments.domain.model.valueobjects.TreatmentCategory;

import java.math.BigDecimal;
import java.util.List;

public record CreateTreatmentCommand(
        String name,
        TreatmentCategory category,
        String description,
        BigDecimal price,
        String duration,
        List<String> materials,
        List<String> steps,
        List<String> animalTypes,
        String image,
        Long createdBy
) {
}