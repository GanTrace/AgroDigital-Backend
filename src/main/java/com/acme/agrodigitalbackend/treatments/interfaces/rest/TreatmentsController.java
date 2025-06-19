package com.acme.agrodigitalbackend.treatments.interfaces.rest;

import com.acme.agrodigitalbackend.treatments.domain.model.commands.DeleteTreatmentCommand;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetAllTreatmentsQuery;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetTreatmentByIdQuery;
import com.acme.agrodigitalbackend.treatments.domain.model.queries.GetTreatmentsByCreatorQuery;
import com.acme.agrodigitalbackend.treatments.domain.services.TreatmentCommandService;
import com.acme.agrodigitalbackend.treatments.domain.services.TreatmentQueryService;
import com.acme.agrodigitalbackend.treatments.interfaces.rest.resources.CreateTreatmentResource;
import com.acme.agrodigitalbackend.treatments.interfaces.rest.resources.TreatmentResource;
import com.acme.agrodigitalbackend.treatments.interfaces.rest.transform.CreateTreatmentCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.treatments.interfaces.rest.transform.TreatmentResourceFromEntityAssembler;
import com.acme.agrodigitalbackend.users.domain.services.UserQueryService;
import com.acme.agrodigitalbackend.users.domain.model.queries.GetUserByIdQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/treatments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Treatments", description = "Treatment Management Endpoints")
public class TreatmentsController {

    private final TreatmentCommandService treatmentCommandService;
    private final TreatmentQueryService treatmentQueryService;
    private final UserQueryService userQueryService;

    public TreatmentsController(TreatmentCommandService treatmentCommandService,
                               TreatmentQueryService treatmentQueryService,
                               UserQueryService userQueryService) {
        this.treatmentCommandService = treatmentCommandService;
        this.treatmentQueryService = treatmentQueryService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    public ResponseEntity<TreatmentResource> createTreatment(@Valid @RequestBody CreateTreatmentResource createTreatmentResource) {
        try {
            // Verificar que el usuario existe
            var userQuery = new GetUserByIdQuery(createTreatmentResource.createdBy());
            var userOptional = userQueryService.handle(userQuery);
            
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            var createTreatmentCommand = CreateTreatmentCommandFromResourceAssembler.toCommandFromResource(createTreatmentResource);
            var treatment = treatmentCommandService.handle(createTreatmentCommand);
            
            if (treatment.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            var treatmentResource = TreatmentResourceFromEntityAssembler.toResourceFromEntity(treatment.get());
            return new ResponseEntity<>(treatmentResource, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating treatment: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TreatmentResource>> getAllTreatments() {
        var getAllTreatmentsQuery = new GetAllTreatmentsQuery();
        var treatments = treatmentQueryService.handle(getAllTreatmentsQuery);
        var treatmentResources = treatments.stream()
                .map(TreatmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(treatmentResources);
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<TreatmentResource> getTreatmentById(@PathVariable Long treatmentId) {
        var getTreatmentByIdQuery = new GetTreatmentByIdQuery(treatmentId);
        var treatment = treatmentQueryService.handle(getTreatmentByIdQuery);
        
        if (treatment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        var treatmentResource = TreatmentResourceFromEntityAssembler.toResourceFromEntity(treatment.get());
        return ResponseEntity.ok(treatmentResource);
    }

    @GetMapping("/creator/{createdBy}")
    public ResponseEntity<List<TreatmentResource>> getTreatmentsByCreator(@PathVariable Long createdBy) {
        var getTreatmentsByCreatorQuery = new GetTreatmentsByCreatorQuery(createdBy);
        var treatments = treatmentQueryService.handle(getTreatmentsByCreatorQuery);
        var treatmentResources = treatments.stream()
                .map(TreatmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(treatmentResources);
    }

    @DeleteMapping("/{treatmentId}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long treatmentId) {
        var deleteTreatmentCommand = new DeleteTreatmentCommand(treatmentId);
        treatmentCommandService.handle(deleteTreatmentCommand);
        return ResponseEntity.noContent().build();
    }
}