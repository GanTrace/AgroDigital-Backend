package com.acme.agrodigitalbackend.treatments.domain.services;

import com.acme.agrodigitalbackend.treatments.domain.model.aggregates.Treatment;
import com.acme.agrodigitalbackend.treatments.domain.model.commands.CreateTreatmentCommand;
import com.acme.agrodigitalbackend.treatments.domain.model.commands.DeleteTreatmentCommand;

import java.util.Optional;

public interface TreatmentCommandService {
    Optional<Treatment> handle(CreateTreatmentCommand command);
    void handle(DeleteTreatmentCommand command);
}