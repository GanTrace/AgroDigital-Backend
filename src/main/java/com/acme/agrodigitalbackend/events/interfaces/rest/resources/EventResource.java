package com.acme.agrodigitalbackend.events.interfaces.rest.resources;

import java.time.LocalDate;

public record EventResource(
        Long id,
        String tipo,
        String titulo,
        LocalDate fecha,
        String descripcion,
        String imagen,
        Long creatorId,
        String creatorName
) {
}