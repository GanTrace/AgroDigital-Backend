package com.acme.agrodigitalbackend.medicalrecords.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.medicalrecords.domain.model.aggregates.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}