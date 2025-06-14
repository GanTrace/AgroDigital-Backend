package com.acme.agrodigitalbackend.animals.interfaces.rest.transform;

import com.acme.agrodigitalbackend.animals.domain.model.commands.CreateAnimalCommand;
import com.acme.agrodigitalbackend.animals.interfaces.rest.resources.CreateAnimalResource;

public class CreateAnimalCommandFromResourceAssembler {
    public static CreateAnimalCommand toCommandFromResource(CreateAnimalResource resource) {
        return new CreateAnimalCommand(
                resource.nombre(),
                resource.especie(),
                resource.fechaNacimiento(),
                resource.sexo(),
                resource.enfermedad(),
                resource.estadoReproductivo(),
                resource.imageURL(),
                resource.vacunasAplicadas(),
                resource.numeroPartos(),
                resource.ubicacion(),
                resource.observaciones(),
                resource.imagen(),
                resource.createdBy()
        );
    }
}