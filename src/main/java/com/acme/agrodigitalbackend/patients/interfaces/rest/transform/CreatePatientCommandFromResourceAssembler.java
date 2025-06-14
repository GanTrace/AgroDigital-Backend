package com.acme.agrodigitalbackend.patients.interfaces.rest.transform;

import com.acme.agrodigitalbackend.patients.domain.model.commands.CreatePatientCommand;
import com.acme.agrodigitalbackend.patients.interfaces.rest.resources.CreatePatientResource;

public class CreatePatientCommandFromResourceAssembler {
    public static CreatePatientCommand toCommandFromResource(CreatePatientResource resource) {
        return new CreatePatientCommand(
                resource.name(),
                resource.type(),
                resource.age(),
                resource.gender(),
                resource.healthIssues(),
                resource.owner(),
                resource.lastVisit(),
                resource.nextVisit(),
                resource.image(),
                resource.observations(),
                resource.createdBy()
        );
    }
}