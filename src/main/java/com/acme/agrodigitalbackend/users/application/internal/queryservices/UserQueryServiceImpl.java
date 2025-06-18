package com.acme.agrodigitalbackend.users.application.internal.queryservices;

import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetAllUsersQuery;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByEmailQuery;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByIdQuery;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUsersByRoleQuery;
import com.acme.agrodigitalbackend.users.domain.services.UserQueryService;
import com.acme.agrodigitalbackend.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail_Address(query.email());
    }

    @Override
    public List<User> handle(GetUsersByRoleQuery query) {
        return userRepository.findByRole(query.role());
    }
}