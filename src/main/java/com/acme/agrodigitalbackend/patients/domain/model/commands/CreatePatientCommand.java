package com.acme.agrodigitalbackend.patients.domain.model.commands;

import java.time.LocalDate;

public record CreatePatientCommand(
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