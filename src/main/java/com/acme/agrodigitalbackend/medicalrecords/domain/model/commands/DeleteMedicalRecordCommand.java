package com.acme.agrodigitalbackend.medicalrecords.domain.model.commands;

public record DeleteMedicalRecordCommand(
        Long medicalRecordId
) {
}