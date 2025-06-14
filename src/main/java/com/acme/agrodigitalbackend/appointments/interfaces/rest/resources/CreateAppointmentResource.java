package com.acme.agrodigitalbackend.appointments.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAppointmentResource(
        @NotNull(message = "Patient ID is mandatory")
        Long patientId,
        
        @NotBlank(message = "Patient name is mandatory")
        String patientName,
        
        @NotBlank(message = "Owner name is mandatory")
        String ownerName,
        
        @NotNull(message = "Date is mandatory")
        LocalDate date,
        
        @NotNull(message = "Time is mandatory")
        LocalTime time,
        
        @NotBlank(message = "Reason is mandatory")
        String reason,
        
        @NotBlank(message = "Status is mandatory")
        String status,
        
        String notes,
        
        @NotNull(message = "Created by is mandatory")
        Long createdBy
) {
}