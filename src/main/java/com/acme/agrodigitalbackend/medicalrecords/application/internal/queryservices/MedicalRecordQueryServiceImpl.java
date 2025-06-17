package com.acme.agrodigitalbackend.medicalrecords.application.internal.queryservices;

import com.acme.agrodigitalbackend.medicalrecords.domain.model.aggregates.MedicalRecord;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.queries.GetAllMedicalRecordsQuery;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.queries.GetMedicalRecordByIdQuery;
import com.acme.agrodigitalbackend.medicalrecords.domain.services.MedicalRecordQueryService;
import com.acme.agrodigitalbackend.medicalrecords.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordQueryServiceImpl implements MedicalRecordQueryService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordQueryServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> handle(GetAllMedicalRecordsQuery query) {
        return medicalRecordRepository.findAll();
    }

    @Override
    public Optional<MedicalRecord> handle(GetMedicalRecordByIdQuery query) {
        return medicalRecordRepository.findById(query.medicalRecordId());
    }
}