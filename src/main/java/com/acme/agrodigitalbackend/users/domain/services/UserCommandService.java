package com.acme.agrodigitalbackend.users.domain.services;

import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import com.acme.agrodigitalbackend.users.domain.model.commands.CreateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
}