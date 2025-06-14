package com.acme.agrodigitalbackend.patients.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.patients.domain.model.aggregates.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}