package com.acme.agrodigitalbackend.users.interfaces.rest.transform;

import com.acme.agrodigitalbackend.users.domain.model.commands.CreateUserCommand;
import com.acme.agrodigitalbackend.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.fullName(),
                resource.email(),
                resource.password()
        );
    }
}