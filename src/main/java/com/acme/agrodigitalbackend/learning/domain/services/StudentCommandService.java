package com.acme.agrodigitalbackend.learning.domain.services;

import com.acme.agrodigitalbackend.learning.domain.model.commands.CreateStudentCommand;
import com.acme.agrodigitalbackend.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.acme.agrodigitalbackend.learning.domain.model.valueobjects.AcmeStudentRecordId;

public interface StudentCommandService {
    AcmeStudentRecordId handle(CreateStudentCommand command);
    AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command);
}

