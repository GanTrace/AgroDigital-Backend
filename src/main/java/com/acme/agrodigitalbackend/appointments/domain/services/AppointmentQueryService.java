package com.acme.agrodigitalbackend.appointments.domain.services;

import com.acme.agrodigitalbackend.appointments.domain.model.aggregates.Appointment;
import com.acme.agrodigitalbackend.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.acme.agrodigitalbackend.appointments.domain.model.queries.GetAppointmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    List<Appointment> handle(GetAllAppointmentsQuery query);
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
}