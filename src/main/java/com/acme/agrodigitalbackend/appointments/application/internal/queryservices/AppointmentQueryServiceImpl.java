package com.acme.agrodigitalbackend.appointments.application.internal.queryservices;

import com.acme.agrodigitalbackend.appointments.domain.model.aggregates.Appointment;
import com.acme.agrodigitalbackend.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.acme.agrodigitalbackend.appointments.domain.model.queries.GetAppointmentByIdQuery;
import com.acme.agrodigitalbackend.appointments.domain.model.queries.GetAppointmentsByCreatorQuery;
import com.acme.agrodigitalbackend.appointments.domain.services.AppointmentQueryService;
import com.acme.agrodigitalbackend.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId());
    }

    @Override
    public List<Appointment> handle(GetAppointmentsByCreatorQuery query) {
        return appointmentRepository.findByCreatedBy(query.createdBy());
    }
}