package com.acme.agrodigitalbackend.events.interfaces.rest.transform;

import com.acme.agrodigitalbackend.events.domain.model.aggregates.Event;
import com.acme.agrodigitalbackend.events.interfaces.rest.resources.EventResource;

public class EventResourceFromEntityAssembler {
    public static EventResource toResourceFromEntity(Event entity) {
        return new EventResource(
                entity.getId(),
                entity.getTipo(),
                entity.getTitulo(),
                entity.getFecha(),
                entity.getDescripcion(),
                entity.getImagen(),
                entity.getCreatorId(),
                entity.getCreatorName()
        );
    }
}