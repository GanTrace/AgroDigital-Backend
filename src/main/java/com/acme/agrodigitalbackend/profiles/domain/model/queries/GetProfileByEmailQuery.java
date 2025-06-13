package com.acme.agrodigitalbackend.profiles.domain.model.queries;

import com.acme.agrodigitalbackend.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}