package com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record MedicalRecordResource(
        Long id,
        Long patientId,
        String patientName,
        String ownerName,
        LocalDate date,
        String recordType,
        String diagnosis,
        String treatment,
        String notes,
        LocalDate followUp,
        List<String> attachments,
        Long createdBy,
        Date createdAt
) {
}