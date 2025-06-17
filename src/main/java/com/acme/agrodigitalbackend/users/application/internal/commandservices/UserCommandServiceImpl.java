package com.acme.agrodigitalbackend.users.application.internal.commandservices;

import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import com.acme.agrodigitalbackend.users.domain.model.commands.CreateUserCommand;
import com.acme.agrodigitalbackend.users.domain.model.commands.UpdateUserCommand;
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

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        var userOptional = userRepository.findById(command.userId());
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        var user = userOptional.get();
        
        // Actualizar solo los campos que no son null o vacíos
        if (command.name() != null && !command.name().trim().isEmpty()) {
            user.updateFullName(command.name());
        }
        
        if (command.email() != null && !command.email().trim().isEmpty()) {
            // Verificar si el nuevo email ya existe (excepto para el usuario actual)
            if (!user.getEmail().equals(command.email()) && userRepository.existsByEmail_Address(command.email())) {
                throw new IllegalArgumentException("User with email " + command.email() + " already exists");
            }
            user.updateEmail(command.email());
        }
        
        if (command.password() != null && !command.password().trim().isEmpty()) {
            user.updatePassword(command.password());
        }
        
        if (command.role() != null && !command.role().trim().isEmpty()) {
            user.updateRole(command.role());
        }
        
        if (command.profileImage() != null) {
            user.updateProfileImageUrl(command.profileImage().trim().isEmpty() ? null : command.profileImage());
        }

        var savedUser = userRepository.save(user);
        return Optional.of(savedUser);
    }
}