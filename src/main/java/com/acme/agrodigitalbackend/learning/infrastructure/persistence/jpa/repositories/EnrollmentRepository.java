package com.acme.agrodigitalbackend.learning.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.learning.domain.model.aggregates.Enrollment;
import com.acme.agrodigitalbackend.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByAcmeStudentRecordId(AcmeStudentRecordId acmeStudentRecordId);
    List<Enrollment> findAllByCourseId(Long courseId);
    Optional<Enrollment> findByAcmeStudentRecordIdAndCourseId(AcmeStudentRecordId acmeStudentRecordId, Long courseId);
}