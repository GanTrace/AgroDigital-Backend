package com.acme.agrodigitalbackend.patients.interfaces.rest.transform;

import com.acme.agrodigitalbackend.patients.domain.model.aggregates.Patient;
import com.acme.agrodigitalbackend.patients.interfaces.rest.resources.PatientResource;

public class PatientResourceFromEntityAssembler {
    public static PatientResource toResourceFromEntity(Patient entity) {
        return new PatientResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getAge(),
                entity.getGender(),
                entity.getHealthIssues(),
                entity.getOwner(),
                entity.getLastVisit(),
                entity.getNextVisit(),
                entity.getImage(),
                entity.getObservations(),
                entity.getCreatedBy()
        );
    }
}