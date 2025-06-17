package com.acme.agrodigitalbackend.medicalrecords.domain.model.commands;

import java.time.LocalDate;
import java.util.List;

public record CreateMedicalRecordCommand(
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
        Long createdBy
) {
}