package com.acme.agrodigitalbackend.events.interfaces.rest.transform;

import com.acme.agrodigitalbackend.events.domain.model.commands.CreateEventCommand;
import com.acme.agrodigitalbackend.events.interfaces.rest.resources.CreateEventResource;

public class CreateEventCommandFromResourceAssembler {
    public static CreateEventCommand toCommandFromResource(CreateEventResource resource) {
        return new CreateEventCommand(
                resource.tipo(),
                resource.titulo(),
                resource.fecha(),
                resource.descripcion(),
                resource.imagen(),
                resource.creatorId(),
                resource.creatorName()
        );
    }
}