package com.acme.agrodigitalbackend.users.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public record CreateUserResource(
        @NotBlank(message = "Name is required")
        @Size(min = 2, message = "Name must be at least 2 characters long")
        String name,
        
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,
        
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).+$", 
                message = "Password must contain letters, numbers and symbols")
        String password,
        
        @NotBlank(message = "Role is required")
        String role
) {
}