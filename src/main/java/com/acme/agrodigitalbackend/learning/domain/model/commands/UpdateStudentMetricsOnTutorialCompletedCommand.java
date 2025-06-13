package com.acme.agrodigitalbackend.learning.domain.model.commands;

import com.acme.agrodigitalbackend.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record UpdateStudentMetricsOnTutorialCompletedCommand(AcmeStudentRecordId studentRecordId) {
}