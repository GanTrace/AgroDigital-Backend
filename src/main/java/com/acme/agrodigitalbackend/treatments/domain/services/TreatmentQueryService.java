package com.acme.agrodigitalbackend.treatments.domain.services;

import com.acme.agrodigitalbackend.treatments.domain.model.aggregates.Treatment;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetAllTreatmentsQuery;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetTreatmentByIdQuery;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetTreatmentsByCreatorQuery;

import java.util.List;
import java.util.Optional;

public interface TreatmentQueryService {
    List<Treatment> handle(GetAllTreatmentsQuery query);
    Optional<Treatment> handle(GetTreatmentByIdQuery query);
    List<Treatment> handle(GetTreatmentsByCreatorQuery query);
}