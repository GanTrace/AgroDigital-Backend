package com.acme.agrodigitalbackend.authentication.interfaces.rest.transform;

import com.acme.agrodigitalbackend.authentication.interfaces.rest.resources.AuthenticationResponse;
import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;

/**
 * AuthenticationResponseFromEntityAssembler
 * <p>
 *     This class is responsible for assembling an AuthenticationResponse from a User entity.
 * </p>
 */
public class AuthenticationResponseFromEntityAssembler {
    
    /**
     * Assembles an AuthenticationResponse from a User entity
     * @param entity the User entity
     * @return the AuthenticationResponse
     */
    public static AuthenticationResponse toResourceFromEntity(User entity) {
        return new AuthenticationResponse(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getRole(),
                entity.getProfileImageUrl(),
                "Login successful"
        );
    }
}