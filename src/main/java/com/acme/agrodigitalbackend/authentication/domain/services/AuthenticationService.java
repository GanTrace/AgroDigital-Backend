package com.acme.agrodigitalbackend.authentication.domain.services;

import com.acme.agrodigitalbackend.authentication.domain.model.commands.LoginCommand;
import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;

import java.util.Optional;

/**
 * AuthenticationService
 * <p>
 *     This interface defines the contract for authentication operations.
 * </p>
 */
public interface AuthenticationService {
    
    /**
     * Authenticates a user with email and password
     * @param command the login command containing email and password
     * @return the authenticated user if credentials are valid, empty otherwise
     */
    Optional<User> authenticate(LoginCommand command);
}