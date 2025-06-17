package com.acme.agrodigitalbackend.treatments.interfaces.rest.transform;

import com.acme.agrodigitalbackend.treatments.domain.model.commands.CreateTreatmentCommand;
import com.acme.agrodigitalbackend.treatments.interfaces.rest.resources.CreateTreatmentResource;

public class CreateTreatmentCommandFromResourceAssembler {
    public static CreateTreatmentCommand toCommandFromResource(CreateTreatmentResource resource) {
        return new CreateTreatmentCommand(
                resource.name(),
                resource.category(),
                resource.description(),
                resource.price(),
                resource.duration(),
                resource.materials(),
                resource.steps(),
                resource.animalTypes(),
                resource.image(),
                resource.createdBy()
        );
    }
}