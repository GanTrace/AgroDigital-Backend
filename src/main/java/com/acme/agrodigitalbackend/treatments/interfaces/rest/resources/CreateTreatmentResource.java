package com.acme.agrodigitalbackend.treatments.interfaces.rest.resources;

import com.acme.agrodigitalbackend.treatments.domain.model.valueobjects.TreatmentCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.util.List;

public record CreateTreatmentResource(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Category is required")
        TreatmentCategory category,
        @NotBlank(message = "Description is required")
        String description,
        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price,
        @NotBlank(message = "Duration is required")
        String duration,
        List<String> materials,
        List<String> steps,
        List<String> animalTypes,
        String image,
        @NotNull(message = "Creator ID is required")
        Long createdBy
) {
}