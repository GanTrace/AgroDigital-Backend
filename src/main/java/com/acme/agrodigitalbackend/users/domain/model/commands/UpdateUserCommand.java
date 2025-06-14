package com.acme.agrodigitalbackend.users.domain.model.commands;

public record UpdateUserCommand(Long userId, String name, String email, String password, String role, String profileImage) {
}