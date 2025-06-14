package com.acme.agrodigitalbackend.users.interfaces.acl;

import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByEmailQuery;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByIdQuery;
import com.acme.agrodigitalbackend.users.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UsersContextFacade
 * <p>
 *     This class is the facade for the Users context. It provides a simplified interface for other contexts to interact with the Users context.
 * </p>
 */
@Service
public class UsersContextFacade {

    private final UserQueryService userQueryService;

    public UsersContextFacade(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    /**
     * Gets a user by id
     * @param userId the user id
     * @return the user if found
     */
    public Optional<User> fetchUserById(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        return userQueryService.handle(getUserByIdQuery);
    }

    /**
     * Gets a user by email
     * @param email the user email
     * @return the user if found
     */
    public Optional<User> fetchUserByEmail(String email) {
        var getUserByEmailQuery = new GetUserByEmailQuery(email);
        return userQueryService.handle(getUserByEmailQuery);
    }

    /**
     * Checks if a user exists by email
     * @param email the user email
     * @return true if the user exists, false otherwise
     */
    public boolean existsUserByEmail(String email) {
        return fetchUserByEmail(email).isPresent();
    }


}