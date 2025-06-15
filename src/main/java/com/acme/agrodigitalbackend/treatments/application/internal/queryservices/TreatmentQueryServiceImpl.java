package com.acme.agrodigitalbackend.treatments.application.internal.queryservices;

import com.acme.agrodigitalbackend.treatments.domain.model.aggregates.Treatment;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetAllTreatmentsQuery;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetTreatmentByIdQuery;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetTreatmentsByCreatorQuery;
import com.acme.agrodigitalbackend.treatments.domain.services.TreatmentQueryService;
import com.acme.agrodigitalbackend.treatments.infrastructure.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentQueryServiceImpl implements TreatmentQueryService {

    private final TreatmentRepository treatmentRepository;

    public TreatmentQueryServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<Treatment> handle(GetAllTreatmentsQuery query) {
        return treatmentRepository.findAll();
    }

    @Override
    public Optional<Treatment> handle(GetTreatmentByIdQuery query) {
        return treatmentRepository.findById(query.treatmentId());
    }

    @Override
    public List<Treatment> handle(GetTreatmentsByCreatorQuery query) {
        return treatmentRepository.findByCreatedBy(query.createdBy());
    }
}