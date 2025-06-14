package com.acme.agrodigitalbackend.patients.domain.services;

import com.acme.agrodigitalbackend.patients.domain.model.aggregates.Patient;
import com.acme.agrodigitalbackend.patients.domain.model.commands.CreatePatientCommand;
import com.acme.agrodigitalbackend.patients.domain.model.commands.DeletePatientCommand;

public interface PatientCommandService {
    Long handle(CreatePatientCommand command);
    void handle(DeletePatientCommand command);
}