package com.acme.agrodigitalbackend.authentication.domain.model.commands;

/**
 * LoginCommand
 * <p>
 *     This record represents the command to authenticate a user with email and password.
 * </p>
 */
public record LoginCommand(String email, String password) {
}