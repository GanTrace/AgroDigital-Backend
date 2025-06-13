package com.acme.agrodigitalbackend.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String password) {
    public Password() { this(null); }

    public Password {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        // Validar que contenga al menos una letra, un número y un símbolo
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).+$")) {
            throw new IllegalArgumentException("Password must contain letters, numbers and symbols");
        }
    }
}