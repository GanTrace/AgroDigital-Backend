package com.acme.agrodigitalbackend.users.domain.model.commands;

public record CreateUserCommand(String name, String email, String password, String role) {
}