package com.acme.agrodigitalbackend.events.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateEventResource(
        String tipo,
        String titulo,
        LocalDate fecha,
        String descripcion,
        String imagen,
        Long creatorId,
        String creatorName
) {
}