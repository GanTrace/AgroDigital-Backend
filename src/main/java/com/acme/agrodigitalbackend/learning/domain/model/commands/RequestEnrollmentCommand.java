package com.acme.agrodigitalbackend.learning.domain.model.commands;

import com.acme.agrodigitalbackend.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record RequestEnrollmentCommand(AcmeStudentRecordId studentRecordId, Long courseId) { }

