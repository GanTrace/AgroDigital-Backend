package com.acme.agrodigitalbackend.patients.application.internal.queryservices;

import com.acme.agrodigitalbackend.patients.domain.model.aggregates.Patient;
import com.acme.agrodigitalbackend.patients.domain.model.queries.GetAllPatientsQuery;
import com.acme.agrodigitalbackend.patients.domain.model.queries.GetPatientByIdQuery;
import com.acme.agrodigitalbackend.patients.domain.services.PatientQueryService;
import com.acme.agrodigitalbackend.patients.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientQueryServiceImpl implements PatientQueryService {

    private final PatientRepository patientRepository;

    public PatientQueryServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> handle(GetAllPatientsQuery query) {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> handle(GetPatientByIdQuery query) {
        return patientRepository.findById(query.patientId());
    }
}