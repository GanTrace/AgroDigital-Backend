package com.acme.agrodigitalbackend.users.application.internal.commandservices;

import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import com.acme.agrodigitalbackend.users.domain.model.commands.CreateUserCommand;
import com.acme.agrodigitalbackend.users.domain.services.UserCommandService;
import com.acme.agrodigitalbackend.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        // Verificar si el email ya existe
        if (userRepository.existsByEmail_Address(command.email())) {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        }

        var user = new User(command);
        var savedUser = userRepository.save(user);
        return Optional.of(savedUser);
    }
}