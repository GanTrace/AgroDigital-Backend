package com.acme.agrodigitalbackend.learning.domain.services;

import com.acme.agrodigitalbackend.learning.domain.model.aggregates.Enrollment;
import com.acme.agrodigitalbackend.learning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface EnrollmentQueryService {
    List<Enrollment> handle(GetAllEnrollmentsByAcmeStudentRecordIdQuery query);
    Optional<Enrollment> handle(GetEnrollmentByIdQuery query);
    List<Enrollment> handle(GetAllEnrollmentsQuery query);
    List<Enrollment> handle(GetAllEnrollmentsByCourseIdQuery query);
    Optional<Enrollment> handle(GetEnrollmentByAcmeStudentRecordIdAndCourseIdQuery query);
}

