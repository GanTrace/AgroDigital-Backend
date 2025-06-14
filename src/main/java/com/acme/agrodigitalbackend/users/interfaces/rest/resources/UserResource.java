package com.acme.agrodigitalbackend.users.interfaces.rest.resources;

public record UserResource(Long id, String name, String email, String password, String role, String profileImage) {
}