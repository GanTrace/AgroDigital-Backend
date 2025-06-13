package com.acme.agrodigitalbackend.learning.interfaces.rest.transform;

import com.acme.agrodigitalbackend.learning.domain.model.commands.CreateStudentCommand;
import com.acme.agrodigitalbackend.learning.interfaces.rest.resources.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource) {
        return new CreateStudentCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.postalCode(),
                resource.country()
        );
    }
}

