package com.acme.agrodigitalbackend.authentication.interfaces.rest.transform;

import com.acme.agrodigitalbackend.authentication.domain.model.commands.LoginCommand;
import com.acme.agrodigitalbackend.authentication.interfaces.rest.resources.LoginResource;

/**
 * LoginCommandFromResourceAssembler
 * <p>
 *     This class is responsible for assembling a LoginCommand from a LoginResource.
 * </p>
 */
public class LoginCommandFromResourceAssembler {
    
    /**
     * Assembles a LoginCommand from a LoginResource
     * @param resource the LoginResource
     * @return the LoginCommand
     */
    public static LoginCommand toCommandFromResource(LoginResource resource) {
        return new LoginCommand(resource.email(), resource.password());
    }
}