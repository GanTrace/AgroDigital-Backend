package com.acme.agrodigitalbackend.users.interfaces.rest.transform;

import com.acme.agrodigitalbackend.users.domain.model.commands.UpdateUserCommand;
import com.acme.agrodigitalbackend.users.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long userId, UpdateUserResource resource) {
        return new UpdateUserCommand(
                userId,
                resource.name(),
                resource.email(),
                resource.password(),
                resource.role(),
                resource.profileImage()
        );
    }
}