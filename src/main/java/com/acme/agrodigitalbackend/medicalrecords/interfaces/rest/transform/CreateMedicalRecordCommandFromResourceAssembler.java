package com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.transform;

import com.acme.agrodigitalbackend.medicalrecords.domain.model.commands.CreateMedicalRecordCommand;
import com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.resources.CreateMedicalRecordResource;

public class CreateMedicalRecordCommandFromResourceAssembler {
    public static CreateMedicalRecordCommand toCommandFromResource(CreateMedicalRecordResource resource) {
        return new CreateMedicalRecordCommand(
                resource.patientId(),
                resource.patientName(),
                resource.ownerName(),
                resource.date(),
                resource.recordType(),
                resource.diagnosis(),
                resource.treatment(),
                resource.notes(),
                resource.followUp(),
                resource.attachments(),
                resource.createdBy()
        );
    }
}