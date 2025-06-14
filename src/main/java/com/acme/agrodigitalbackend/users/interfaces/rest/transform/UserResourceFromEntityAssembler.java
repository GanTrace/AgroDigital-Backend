package com.acme.agrodigitalbackend.users.interfaces.rest.transform;

import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import com.acme.agrodigitalbackend.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPasswordValue(),
                entity.getRole()
        );
    }
}