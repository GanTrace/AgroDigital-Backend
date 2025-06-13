package com.acme.agrodigitalbackend.learning.interfaces.rest.transform;

import com.acme.agrodigitalbackend.learning.domain.model.aggregates.Course;
import com.acme.agrodigitalbackend.learning.interfaces.rest.resources.CourseResource;

public class CourseResourceFromEntityAssembler {
    public static CourseResource toResourceFromEntity(Course entity) {
        return new CourseResource(entity.getId(), entity.getTitle(), entity.getDescription());
    }
}

