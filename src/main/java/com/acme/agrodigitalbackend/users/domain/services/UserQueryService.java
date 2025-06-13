package com.acme.agrodigitalbackend.users.domain.services;

import com.acme.agrodigitalbackend.users.domain.model.aggregates.User;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetAllUsersQuery;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByEmailQuery;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByEmailQuery query);
}