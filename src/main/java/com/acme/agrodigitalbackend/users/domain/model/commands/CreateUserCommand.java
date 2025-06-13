package com.acme.agrodigitalbackend.users.domain.model.commands;

public record CreateUserCommand(String fullName, String email, String password) {
}