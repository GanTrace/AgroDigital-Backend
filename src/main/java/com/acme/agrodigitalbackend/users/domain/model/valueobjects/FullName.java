package com.acme.agrodigitalbackend.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FullName(String fullName) {
    public FullName() { this(null); }

    public FullName {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be null or blank");
        }
        if (fullName.trim().length() < 2) {
            throw new IllegalArgumentException("Full name must be at least 2 characters long");
        }
    }
}