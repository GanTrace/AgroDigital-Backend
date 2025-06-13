package com.acme.agrodigitalbackend.profiles.interfaces.rest.transform;

import com.acme.agrodigitalbackend.profiles.domain.model.aggregates.Profile;
import com.acme.agrodigitalbackend.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getFullName(), entity.getEmailAddress(), entity.getStreetAddress());
    }
}

