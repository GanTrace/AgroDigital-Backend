package com.acme.agrodigitalbackend.profiles.domain.services;

import com.acme.agrodigitalbackend.profiles.domain.model.aggregates.Profile;
import com.acme.agrodigitalbackend.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}

