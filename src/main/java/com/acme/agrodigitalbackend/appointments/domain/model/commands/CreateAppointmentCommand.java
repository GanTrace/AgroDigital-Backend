package com.acme.agrodigitalbackend.appointments.domain.model.commands;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAppointmentCommand(
        Long patientId,
        String patientName,
        String ownerName,
        LocalDate date,
        LocalTime time,
        String reason,
        String status,
        String notes,
        Long createdBy
) {
}