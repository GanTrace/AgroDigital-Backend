package com.acme.agrodigitalbackend.treatments.interfaces.rest.resources;

import com.acme.agrodigitalbackend.treatments.domain.model.valueobjects.TreatmentCategory;

import java.math.BigDecimal;
import java.util.List;

public record CreateTreatmentResource(
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