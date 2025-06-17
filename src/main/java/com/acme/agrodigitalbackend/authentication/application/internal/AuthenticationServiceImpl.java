package com.acme.agrodigitalbackend.authentication.application.internal;

import com.acme.agrodigitalbackend.authentication.domain.model.commands.LoginCommand;
import com.acme.agrodigitalbackend.authentication.domain.services.AuthenticationService;
import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByEmailQuery;
import com.acme.agrodigitalbackend.users.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AuthenticationServiceImpl
 * <p>
 *     This class implements the AuthenticationService interface and provides
 *     the logic for user authentication.
 * </p>
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    
    private final UserQueryService userQueryService;
    
    public AuthenticationServiceImpl(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }
    
    @Override
    public Optional<User> authenticate(LoginCommand command) {
        // Find user by email
        var getUserByEmailQuery = new GetUserByEmailQuery(command.email());
        var userOptional = userQueryService.handle(getUserByEmailQuery);
        
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        
        var user = userOptional.get();
        
        // Validate password
        if (user.getPasswordValue().equals(command.password())) {
            return Optional.of(user);
        }
        
        return Optional.empty();
    }
}