package com.acme.agrodigitalbackend.animals.interfaces.rest;

import com.acme.agrodigitalbackend.animals.domain.model.commands.DeleteAnimalCommand;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAllAnimalsQuery;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAnimalByIdQuery;
import com.acme.agrodigitalbackend.animals.domain.model.queries.GetAnimalsByCreatorQuery;
import com.acme.agrodigitalbackend.animals.domain.services.AnimalCommandService;
import com.acme.agrodigitalbackend.animals.domain.services.AnimalQueryService;
import com.acme.agrodigitalbackend.animals.interfaces.rest.resources.CreateAnimalResource;
import com.acme.agrodigitalbackend.animals.interfaces.rest.resources.AnimalResource;
import com.acme.agrodigitalbackend.animals.interfaces.rest.transform.CreateAnimalCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.animals.interfaces.rest.transform.AnimalResourceFromEntityAssembler;
import com.acme.agrodigitalbackend.users.domain.services.UserQueryService;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByIdQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/animals", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Animals", description = "Animal Management Endpoints")
public class AnimalsController {

    private final AnimalCommandService animalCommandService;
    private final AnimalQueryService animalQueryService;
    private final UserQueryService userQueryService;

    public AnimalsController(AnimalCommandService animalCommandService, 
                           AnimalQueryService animalQueryService,
                           UserQueryService userQueryService) {
        this.animalCommandService = animalCommandService;
        this.animalQueryService = animalQueryService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    public ResponseEntity<AnimalResource> createAnimal(@RequestBody CreateAnimalResource createAnimalResource) {
        // Verificar que el usuario existe
        var userQuery = new GetUserByIdQuery(createAnimalResource.createdBy());
        var userOptional = userQueryService.handle(userQuery);
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        var createAnimalCommand = CreateAnimalCommandFromResourceAssembler.toCommandFromResource(createAnimalResource);
        var animal = animalCommandService.handle(createAnimalCommand);
        
        if (animal.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        var animalResource = AnimalResourceFromEntityAssembler.toResourceFromEntity(animal.get());
        return new ResponseEntity<>(animalResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnimalResource>> getAllAnimals() {
        var getAllAnimalsQuery = new GetAllAnimalsQuery();
        var animals = animalQueryService.handle(getAllAnimalsQuery);
        var animalResources = animals.stream()
                .map(AnimalResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(animalResources);
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalResource> getAnimalById(@PathVariable Long animalId) {
        var getAnimalByIdQuery = new GetAnimalByIdQuery(animalId);
        var animal = animalQueryService.handle(getAnimalByIdQuery);
        
        if (animal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        var animalResource = AnimalResourceFromEntityAssembler.toResourceFromEntity(animal.get());
        return ResponseEntity.ok(animalResource);
    }

    @GetMapping("/creator/{createdBy}")
    public ResponseEntity<List<AnimalResource>> getAnimalsByCreator(@PathVariable Long createdBy) {
        var getAnimalsByCreatorQuery = new GetAnimalsByCreatorQuery(createdBy);
        var animals = animalQueryService.handle(getAnimalsByCreatorQuery);
        var animalResources = animals.stream()
                .map(AnimalResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(animalResources);
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long animalId) {
        var deleteAnimalCommand = new DeleteAnimalCommand(animalId);
        animalCommandService.handle(deleteAnimalCommand);
        return ResponseEntity.noContent().build();
    }
}