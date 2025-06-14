package com.acme.agrodigitalbackend.animals.interfaces.rest.transform;

import com.acme.agrodigitalbackend.animals.domain.model.aggregates.Animal;
import com.acme.agrodigitalbackend.animals.interfaces.rest.resources.AnimalResource;

public class AnimalResourceFromEntityAssembler {
    public static AnimalResource toResourceFromEntity(Animal entity) {
        return new AnimalResource(
                entity.getId(),
                entity.getNombre(),
                entity.getEspecie(),
                entity.getFechaNacimiento(),
                entity.getSexo(),
                entity.getEnfermedad(),
                entity.getEstadoReproductivo(),
                entity.getImageURL(),
                entity.getVacunasAplicadas(),
                entity.getNumeroPartos(),
                entity.getUbicacion(),
                entity.getObservaciones(),
                entity.getImagen(),
                entity.getCreatedBy()
        );
    }
}