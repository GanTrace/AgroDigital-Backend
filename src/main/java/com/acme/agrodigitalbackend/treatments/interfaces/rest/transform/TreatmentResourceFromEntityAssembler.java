package com.acme.agrodigitalbackend.treatments.interfaces.rest.transform;

import com.acme.agrodigitalbackend.treatments.domain.model.aggregates.Treatment;
import com.acme.agrodigitalbackend.treatments.interfaces.rest.resources.TreatmentResource;

public class TreatmentResourceFromEntityAssembler {
    public static TreatmentResource toResourceFromEntity(Treatment entity) {
        return new TreatmentResource(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getDuration(),
                entity.getMaterials(),
                entity.getSteps(),
                entity.getAnimalTypes(),
                entity.getImage(),
                entity.getCreatedBy(),
                entity.getCreatedAt()
        );
    }
}