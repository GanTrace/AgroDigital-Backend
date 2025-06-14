package com.acme.agrodigitalbackend.appointments.interfaces.rest.transform;

import com.acme.agrodigitalbackend.appointments.domain.model.aggregates.Appointment;
import com.acme.agrodigitalbackend.appointments.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity) {
        return new AppointmentResource(
                entity.getId(),
                entity.getPatientId(),
                entity.getPatientName(),
                entity.getOwnerName(),
                entity.getDate(),
                entity.getTime(),
                entity.getReason(),
                entity.getStatus(),
                entity.getNotes(),
                entity.getCreatedBy()
        );
    }
}