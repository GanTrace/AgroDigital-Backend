package com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record CreateMedicalRecordResource(
        @NotNull(message = "Patient ID is required")
        Long patientId,
        
        @NotBlank(message = "Patient name is required")
        String patientName,
        
        @NotBlank(message = "Owner name is required")
        String ownerName,
        
        @NotNull(message = "Date is required")
        LocalDate date,
        
        @NotBlank(message = "Record type is required")
        String recordType,
        
        @NotBlank(message = "Diagnosis is required")
        String diagnosis,
        
        @NotBlank(message = "Treatment is required")
        String treatment,
        
        String notes,
        
        LocalDate followUp,
        
        List<String> attachments,
        
        @NotNull(message = "Created by is required")
        Long createdBy
) {
}