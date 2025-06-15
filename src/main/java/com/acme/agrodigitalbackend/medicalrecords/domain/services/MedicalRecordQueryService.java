package com.acme.agrodigitalbackend.medicalrecords.domain.services;

import com.acme.agrodigitalbackend.medicalrecords.domain.model.aggregates.MedicalRecord;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.queries.GetAllMedicalRecordsQuery;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.queries.GetMedicalRecordByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordQueryService {
    List<MedicalRecord> handle(GetAllMedicalRecordsQuery query);
    Optional<MedicalRecord> handle(GetMedicalRecordByIdQuery query);
}