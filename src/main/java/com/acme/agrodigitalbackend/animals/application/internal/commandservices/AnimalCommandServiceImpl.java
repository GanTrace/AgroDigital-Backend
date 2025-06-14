package com.acme.agrodigitalbackend.animals.application.internal.commandservices;

import com.acme.agrodigitalbackend.animals.domain.model.aggregates.Animal;
import com.acme.agrodigitalbackend.animals.domain.model.commands.CreateAnimalCommand;
import com.acme.agrodigitalbackend.animals.domain.model.commands.DeleteAnimalCommand;
import com.acme.agrodigitalbackend.animals.domain.services.AnimalCommandService;
import com.acme.agrodigitalbackend.animals.infrastructure.persistence.jpa.repositories.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalCommandServiceImpl implements AnimalCommandService {

    private final AnimalRepository animalRepository;

    public AnimalCommandServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Optional<Animal> handle(CreateAnimalCommand command) {
        var animal = new Animal(command);
        var createdAnimal = animalRepository.save(animal);
        return Optional.of(createdAnimal);
    }

    @Override
    public void handle(DeleteAnimalCommand command) {
        animalRepository.deleteById(command.animalId());
    }
}