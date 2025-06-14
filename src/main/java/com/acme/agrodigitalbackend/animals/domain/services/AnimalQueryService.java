package com.acme.agrodigitalbackend.animals.domain.services;

import com.acme.agrodigitalbackend.animals.domain.model.aggregates.Animal;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAllAnimalsQuery;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAnimalByIdQuery;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAnimalsByCreatorQuery;

import java.util.List;
import java.util.Optional;

public interface AnimalQueryService {
    List<Animal> handle(GetAllAnimalsQuery query);
    Optional<Animal> handle(GetAnimalByIdQuery query);
    List<Animal> handle(GetAnimalsByCreatorQuery query);
}