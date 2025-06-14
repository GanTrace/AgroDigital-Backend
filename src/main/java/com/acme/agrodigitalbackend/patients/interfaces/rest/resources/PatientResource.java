package com.acme.agrodigitalbackend.patients.interfaces.rest.resources;

import java.time.LocalDate;

public record PatientResource(
        Long id,
        String name,
        String type,
        String age,
        String gender,
        String healthIssues,
        String owner,
        LocalDate lastVisit,
        LocalDate nextVisit,
        String image,
        String observations,
        Long createdBy
) {
}