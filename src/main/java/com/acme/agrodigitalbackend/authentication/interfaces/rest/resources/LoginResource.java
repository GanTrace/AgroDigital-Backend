package com.acme.agrodigitalbackend.authentication.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * LoginResource
 * <p>
 *     This record represents the resource for user login containing email and password.
 * </p>
 */
public record LoginResource(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,
        
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password
) {
}