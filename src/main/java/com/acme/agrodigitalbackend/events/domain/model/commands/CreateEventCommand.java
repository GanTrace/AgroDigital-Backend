package com.acme.agrodigitalbackend.events.domain.model.commands;

import java.time.LocalDate;

public record CreateEventCommand(
        String tipo,
        String titulo,
        LocalDate fecha,
        String descripcion,
        String imagen,
        Long creatorId,
        String creatorName
) {
}