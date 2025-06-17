package com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.transform;

import com.acme.agrodigitalbackend.medicalrecords.domain.model.aggregates.MedicalRecord;
import com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.resources.MedicalRecordResource;

public class MedicalRecordResourceFromEntityAssembler {
    public static MedicalRecordResource toResourceFromEntity(MedicalRecord entity) {
        return new MedicalRecordResource(
                entity.getId(),
                entity.getPatientId(),
                entity.getPatientName(),
                entity.getOwnerName(),
                entity.getDate(),
                entity.getRecordType(),
                entity.getDiagnosis(),
                entity.getTreatment(),
                entity.getNotes(),
                entity.getFollowUp(),
                entity.getAttachments(),
                entity.getCreatedBy(),
                entity.getCreatedAt()
        );
    }
}