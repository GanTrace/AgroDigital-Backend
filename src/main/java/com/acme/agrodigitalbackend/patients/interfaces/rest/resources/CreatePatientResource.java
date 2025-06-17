package com.acme.agrodigitalbackend.patients.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreatePatientResource(
        @NotBlank(message = "Name is mandatory")
        String name,
        
        @NotBlank(message = "Type is mandatory")
        String type,
        
        @NotBlank(message = "Age is mandatory")
        String age,
        
        @NotBlank(message = "Gender is mandatory")
        String gender,
        
        @NotBlank(message = "Health issues is mandatory")
        String healthIssues,
        
        @NotBlank(message = "Owner is mandatory")
        String owner,
        
        LocalDate lastVisit,
        
        LocalDate nextVisit,
        
        String image,
        
        String observations,
        
        @NotNull(message = "Created by is mandatory")
        Long createdBy
) {
}