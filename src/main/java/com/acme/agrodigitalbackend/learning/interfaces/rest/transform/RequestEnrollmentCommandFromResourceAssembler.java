package com.acme.agrodigitalbackend.learning.interfaces.rest.transform;

import com.acme.agrodigitalbackend.learning.domain.model.commands.RequestEnrollmentCommand;
import com.acme.agrodigitalbackend.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.agrodigitalbackend.learning.interfaces.rest.resources.RequestEnrollmentResource;

public class RequestEnrollmentCommandFromResourceAssembler {
    public static RequestEnrollmentCommand toCommandFromResource(RequestEnrollmentResource resource) {
        return new RequestEnrollmentCommand(new AcmeStudentRecordId(resource.studentRecordId()), resource.courseId());
    }
}