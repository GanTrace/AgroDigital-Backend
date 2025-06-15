package com.acme.agrodigitalbackend.medicalrecords.domain.services;

import com.acme.agrodigitalbackend.medicalrecords.domain.model.commands.CreateMedicalRecordCommand;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.commands.DeleteMedicalRecordCommand;

public interface MedicalRecordCommandService {
    Long handle(CreateMedicalRecordCommand command);
    void handle(DeleteMedicalRecordCommand command);
}