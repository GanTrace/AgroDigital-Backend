package com.acme.agrodigitalbackend.profiles.domain.services;

import com.acme.agrodigitalbackend.profiles.domain.model.aggregates.Profile;
import com.acme.agrodigitalbackend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.acme.agrodigitalbackend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.agrodigitalbackend.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Profile> handle(GetProfileByIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);
}

