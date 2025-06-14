package com.acme.agrodigitalbackend.animals.application.internal.queryservices;

import com.acme.agrodigitalbackend.animals.domain.model.aggregates.Animal;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAllAnimalsQuery;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAnimalByIdQuery;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAnimalsByCreatorQuery;
import com.acme.agrodigitalbackend.animals.domain.services.AnimalQueryService;
import com.acme.agrodigitalbackend.animals.infrastructure.persistence.jpa.repositories.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalQueryServiceImpl implements AnimalQueryService {

    private final AnimalRepository animalRepository;

    public AnimalQueryServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> handle(GetAllAnimalsQuery query) {
        return animalRepository.findAll();
    }

    @Override
    public Optional<Animal> handle(GetAnimalByIdQuery query) {
        return animalRepository.findById(query.animalId());
    }

    @Override
    public List<Animal> handle(GetAnimalsByCreatorQuery query) {
        return animalRepository.findByCreatedBy(query.createdBy());
    }
}