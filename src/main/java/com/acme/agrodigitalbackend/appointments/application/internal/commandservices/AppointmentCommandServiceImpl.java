package com.acme.agrodigitalbackend.appointments.application.internal.commandservices;

import com.acme.agrodigitalbackend.appointments.domain.model.aggregates.Appointment;
import com.acme.agrodigitalbackend.appointments.domain.model.commands.CreateAppointmentCommand;
import com.acme.agrodigitalbackend.appointments.domain.model.commands.DeleteAppointmentCommand;
import com.acme.agrodigitalbackend.appointments.domain.services.AppointmentCommandService;
import com.acme.agrodigitalbackend.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Long handle(CreateAppointmentCommand command) {
        var appointment = new Appointment(command);
        appointmentRepository.save(appointment);
        return appointment.getId();
    }


    @Override
    public void handle(DeleteAppointmentCommand command) {
        appointmentRepository.deleteById(command.appointmentId());
    }
}