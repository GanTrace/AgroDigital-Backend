package com.acme.agrodigitalbackend.patients.domain.services;

import com.acme.agrodigitalbackend.patients.domain.model.aggregates.Patient;
import com.acme.agrodigitalbackend.patients.domain.model.queries.GetAllPatientsQuery;
import com.acme.agrodigitalbackend.patients.domain.model.queries.GetPatientByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PatientQueryService {
    List<Patient> handle(GetAllPatientsQuery query);
    Optional<Patient> handle(GetPatientByIdQuery query);
}