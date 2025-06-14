package com.acme.agrodigitalbackend.patients.application.internal.commandservices;

import com.acme.agrodigitalbackend.patients.domain.model.aggregates.Patient;
import com.acme.agrodigitalbackend.patients.domain.model.commands.CreatePatientCommand;
import com.acme.agrodigitalbackend.patients.domain.model.commands.DeletePatientCommand;
import com.acme.agrodigitalbackend.patients.domain.services.PatientCommandService;
import com.acme.agrodigitalbackend.patients.infrastructure.persistence.jpa.repositories.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientCommandServiceImpl implements PatientCommandService {

    private final PatientRepository patientRepository;

    public PatientCommandServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Long handle(CreatePatientCommand command) {
        var patient = new Patient(command);
        patientRepository.save(patient);
        return patient.getId();
    }

    @Override
    public void handle(DeletePatientCommand command) {
        patientRepository.deleteById(command.patientId());
    }
}