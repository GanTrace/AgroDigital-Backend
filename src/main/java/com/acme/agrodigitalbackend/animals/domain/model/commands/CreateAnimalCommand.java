package com.acme.agrodigitalbackend.animals.domain.model.commands;

import java.time.LocalDate;

public record CreateAnimalCommand(
        String nombre,
        String especie,
        LocalDate fechaNacimiento,
        String sexo,
        String enfermedad,
        String estadoReproductivo,
        String imageURL,
        Integer vacunasAplicadas,
        Integer numeroPartos,
        String ubicacion,
        String observaciones,
        String imagen,
        Long createdBy
) {
}