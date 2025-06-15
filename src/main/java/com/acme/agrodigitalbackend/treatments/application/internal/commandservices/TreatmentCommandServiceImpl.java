package com.acme.agrodigitalbackend.treatments.application.internal.commandservices;

import com.acme.agrodigitalbackend.treatments.domain.model.aggregates.Treatment;
import com.acme.agrodigitalbackend.treatments.domain.model.commands.CreateTreatmentCommand;
import com.acme.agrodigitalbackend.treatments.domain.model.commands.DeleteTreatmentCommand;
import com.acme.agrodigitalbackend.treatments.domain.services.TreatmentCommandService;
import com.acme.agrodigitalbackend.treatments.infrastructure.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreatmentCommandServiceImpl implements TreatmentCommandService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentCommandServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Optional<Treatment> handle(CreateTreatmentCommand command) {
        var treatment = new Treatment(command);
        try {
            treatmentRepository.save(treatment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving treatment: " + e.getMessage());
        }
        return Optional.of(treatment);
    }

    @Override
    public void handle(DeleteTreatmentCommand command) {
        treatmentRepository.deleteById(command.treatmentId());
    }
}