package com.acme.agrodigitalbackend.appointments.interfaces.rest.transform;

import com.acme.agrodigitalbackend.appointments.domain.model.commands.CreateAppointmentCommand;
import com.acme.agrodigitalbackend.appointments.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(
                resource.patientId(),
                resource.patientName(),
                resource.ownerName(),
                resource.date(),
                resource.time(),
                resource.reason(),
                resource.status(),
                resource.notes(),
                resource.createdBy()
        );
    }
}