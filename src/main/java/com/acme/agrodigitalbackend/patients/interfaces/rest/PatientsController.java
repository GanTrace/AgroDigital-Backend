package com.acme.agrodigitalbackend.patients.interfaces.rest;

import com.acme.agrodigitalbackend.patients.domain.model.commands.DeletePatientCommand;
import com.acme.agrodigitalbackend.patients.domain.model.queries.GetAllPatientsQuery;
import com.acme.agrodigitalbackend.patients.domain.model.queries.GetPatientByIdQuery;
import com.acme.agrodigitalbackend.patients.domain.services.PatientCommandService;
import com.acme.agrodigitalbackend.patients.domain.services.PatientQueryService;
import com.acme.agrodigitalbackend.patients.interfaces.rest.resources.CreatePatientResource;
import com.acme.agrodigitalbackend.patients.interfaces.rest.resources.PatientResource;
import com.acme.agrodigitalbackend.patients.interfaces.rest.transform.CreatePatientCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.patients.interfaces.rest.transform.PatientResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/patients", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Patients", description = "Patients Management Endpoints")
public class PatientsController {
    private final PatientCommandService patientCommandService;
    private final PatientQueryService patientQueryService;

    public PatientsController(PatientCommandService patientCommandService, PatientQueryService patientQueryService) {
        this.patientCommandService = patientCommandService;
        this.patientQueryService = patientQueryService;
    }

    @PostMapping
    public ResponseEntity<PatientResource> createPatient(@Valid @RequestBody CreatePatientResource createPatientResource) {
        var createPatientCommand = CreatePatientCommandFromResourceAssembler.toCommandFromResource(createPatientResource);
        var patientId = patientCommandService.handle(createPatientCommand);
        if (patientId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getPatientByIdQuery = new GetPatientByIdQuery(patientId);
        var patient = patientQueryService.handle(getPatientByIdQuery);
        if (patient.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());
        return new ResponseEntity<>(patientResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatientResource>> getAllPatients() {
        var getAllPatientsQuery = new GetAllPatientsQuery();
        var patients = patientQueryService.handle(getAllPatientsQuery);
        var patientResources = patients.stream()
                .map(PatientResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(patientResources);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResource> getPatientById(@PathVariable Long patientId) {
        var getPatientByIdQuery = new GetPatientByIdQuery(patientId);
        var patient = patientQueryService.handle(getPatientByIdQuery);
        if (patient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var patientResource = PatientResourceFromEntityAssembler.toResourceFromEntity(patient.get());
        return ResponseEntity.ok(patientResource);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Long patientId) {
        var deletePatientCommand = new DeletePatientCommand(patientId);
        patientCommandService.handle(deletePatientCommand);
        return ResponseEntity.ok("Patient with given id successfully deleted");
    }
}