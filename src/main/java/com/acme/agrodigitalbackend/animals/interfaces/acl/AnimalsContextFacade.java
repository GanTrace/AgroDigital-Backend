package com.acme.agrodigitalbackend.animals.interfaces.acl;

import com.acme.agrodigitalbackend.animals.domain.model.aggregates.Animal;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAnimalByIdQuery;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAnimalsByCreatorQuery;
import com.acme.agrodigitalbackend.animals.domain.services.AnimalQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * AnimalsContextFacade
 * <p>
 * This class is a facade for the Animals context. It provides a simple interface for other contexts to interact with the Animals context.
 * </p>
 */
@Service
public class AnimalsContextFacade {

    private final AnimalQueryService animalQueryService;

    public AnimalsContextFacade(AnimalQueryService animalQueryService) {
        this.animalQueryService = animalQueryService;
    }

    /**
     * Get animal by id
     * @param animalId the animal id
     * @return the animal
     */
    public Optional<Animal> fetchAnimalById(Long animalId) {
        var getAnimalByIdQuery = new GetAnimalByIdQuery(animalId);
        return animalQueryService.handle(getAnimalByIdQuery);
    }

    /**
     * Get animals by creator id
     * @param createdBy the creator id
     * @return the list of animals
     */
    public List<Animal> fetchAnimalsByCreator(Long createdBy) {
        var getAnimalsByCreatorQuery = new GetAnimalsByCreatorQuery(createdBy);
        return animalQueryService.handle(getAnimalsByCreatorQuery);
    }
}