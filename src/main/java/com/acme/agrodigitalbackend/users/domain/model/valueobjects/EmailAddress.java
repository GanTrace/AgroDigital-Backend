package com.acme.agrodigitalbackend.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record EmailAddress(
        @NotBlank
        @Email
        String address
) {
    public EmailAddress {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Email address cannot be null or blank");
        }
    }

    public EmailAddress() {
        this("");
    }
}