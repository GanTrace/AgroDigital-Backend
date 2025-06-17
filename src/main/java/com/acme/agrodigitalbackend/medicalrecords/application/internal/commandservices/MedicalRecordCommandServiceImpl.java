package com.acme.agrodigitalbackend.medicalrecords.application.internal.commandservices;

import com.acme.agrodigitalbackend.medicalrecords.domain.model.aggregates.MedicalRecord;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.commands.CreateMedicalRecordCommand;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.commands.DeleteMedicalRecordCommand;
import com.acme.agrodigitalbackend.medicalrecords.domain.services.MedicalRecordCommandService;
import com.acme.agrodigitalbackend.medicalrecords.infrastructure.persistence.jpa.repositories.MedicalRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordCommandServiceImpl implements MedicalRecordCommandService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordCommandServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public Long handle(CreateMedicalRecordCommand command) {
        var medicalRecord = new MedicalRecord(command);
        medicalRecordRepository.save(medicalRecord);
        return medicalRecord.getId();
    }

    @Override
    public void handle(DeleteMedicalRecordCommand command) {
        medicalRecordRepository.deleteById(command.medicalRecordId());
    }
}