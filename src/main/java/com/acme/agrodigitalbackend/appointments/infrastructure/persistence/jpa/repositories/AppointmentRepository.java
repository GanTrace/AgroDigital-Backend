package com.acme.agrodigitalbackend.appointments.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.appointments.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}