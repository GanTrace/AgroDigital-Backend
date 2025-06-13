package com.acme.agrodigitalbackend.learning.interfaces.rest.resources;

public record StudentResource(String acmeStudentRecordId, Long profileId, Integer totalCompletedCourses, Integer totalTutorials) {
}