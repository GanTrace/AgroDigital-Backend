package com.acme.agrodigitalbackend.animals.domain.services;

import com.acme.agrodigitalbackend.animals.domain.model.aggregates.Animal;
import com.acme.agrodigitalbackend.animals.domain.model.commands.CreateAnimalCommand;
import com.acme.agrodigitalbackend.animals.domain.model.commands.DeleteAnimalCommand;

import java.util.Optional;

public interface AnimalCommandService {
    Optional<Animal> handle(CreateAnimalCommand command);
    void handle(DeleteAnimalCommand command);
}