package com.acme.agrodigitalbackend.users.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserResource(
        @Size(min = 2, message = "Name must be at least 2 characters long")
        String name,
        
        @Email(message = "Email must be valid")
        String email,
        
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).+$", 
                message = "Password must contain letters, numbers and symbols")
        String password,
        
        String role,
        
        String profileImage
) {
}