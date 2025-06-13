package com.acme.agrodigitalbackend.learning.interfaces.rest.transform;

import com.acme.agrodigitalbackend.learning.domain.model.aggregates.Enrollment;
import com.acme.agrodigitalbackend.learning.interfaces.rest.resources.EnrollmentResource;

/**
 * EnrollmentResourceFromEntityAssembler.
 * <p>
 * This class is used to transform an Enrollment entity into an EnrollmentResource.
 * </p>
 */
public class EnrollmentResourceFromEntityAssembler {
    /**
     * Transform an Enrollment entity into an EnrollmentResource.
     *
     * @param entity Enrollment entity to be transformed.
     * @return EnrollmentResource the resulting resource.
     */
    public static EnrollmentResource toResourceFromEntity(Enrollment entity) {
        return new EnrollmentResource(entity.getId(), entity.getAcmeStudentRecordId().studentRecordId(), entity.getCourse().getId(), entity.getStatus());
    }
}

