package com.acme.agrodigitalbackend.treatments.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.treatments.domain.model.aggregates.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByCreatedBy(Long createdBy);
}