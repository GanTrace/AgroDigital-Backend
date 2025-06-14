package com.acme.agrodigitalbackend.appointments.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResource(
        Long id,
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