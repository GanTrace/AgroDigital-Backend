package com.acme.agrodigitalbackend.learning.domain.model.queries;

import com.acme.agrodigitalbackend.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record GetAllEnrollmentsByAcmeStudentRecordIdQuery(AcmeStudentRecordId studentRecordId) {
}

