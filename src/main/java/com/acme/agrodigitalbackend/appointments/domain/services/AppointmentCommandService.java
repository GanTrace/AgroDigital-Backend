package com.acme.agrodigitalbackend.appointments.domain.services;

import com.acme.agrodigitalbackend.appointments.domain.model.commands.CreateAppointmentCommand;
import com.acme.agrodigitalbackend.appointments.domain.model.commands.DeleteAppointmentCommand;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
    void handle(DeleteAppointmentCommand command);
}