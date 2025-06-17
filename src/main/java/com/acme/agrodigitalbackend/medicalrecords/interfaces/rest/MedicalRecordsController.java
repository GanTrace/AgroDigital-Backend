package com.acme.agrodigitalbackend.medicalrecords.interfaces.rest;

import com.acme.agrodigitalbackend.medicalrecords.domain.model.commands.DeleteMedicalRecordCommand;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.queries.GetAllMedicalRecordsQuery;
import com.acme.agrodigitalbackend.medicalrecords.domain.model.queries.GetMedicalRecordByIdQuery;
import com.acme.agrodigitalbackend.medicalrecords.domain.services.MedicalRecordCommandService;
import com.acme.agrodigitalbackend.medicalrecords.domain.services.MedicalRecordQueryService;
import com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.resources.CreateMedicalRecordResource;
import com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.resources.MedicalRecordResource;
import com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.transform.CreateMedicalRecordCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.medicalrecords.interfaces.rest.transform.MedicalRecordResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/medical-records", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Medical Records", description = "Medical Records Management Endpoints")
public class MedicalRecordsController {
    private final MedicalRecordCommandService medicalRecordCommandService;
    private final MedicalRecordQueryService medicalRecordQueryService;

    public MedicalRecordsController(MedicalRecordCommandService medicalRecordCommandService, MedicalRecordQueryService medicalRecordQueryService) {
        this.medicalRecordCommandService = medicalRecordCommandService;
        this.medicalRecordQueryService = medicalRecordQueryService;
    }

    @PostMapping
    public ResponseEntity<MedicalRecordResource> createMedicalRecord(@Valid @RequestBody CreateMedicalRecordResource createMedicalRecordResource) {
        var createMedicalRecordCommand = CreateMedicalRecordCommandFromResourceAssembler.toCommandFromResource(createMedicalRecordResource);
        var medicalRecordId = medicalRecordCommandService.handle(createMedicalRecordCommand);
        if (medicalRecordId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getMedicalRecordByIdQuery = new GetMedicalRecordByIdQuery(medicalRecordId);
        var medicalRecord = medicalRecordQueryService.handle(getMedicalRecordByIdQuery);
        if (medicalRecord.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var medicalRecordResource = MedicalRecordResourceFromEntityAssembler.toResourceFromEntity(medicalRecord.get());
        return new ResponseEntity<>(medicalRecordResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordResource>> getAllMedicalRecords() {
        var getAllMedicalRecordsQuery = new GetAllMedicalRecordsQuery();
        var medicalRecords = medicalRecordQueryService.handle(getAllMedicalRecordsQuery);
        var medicalRecordResources = medicalRecords.stream()
                .map(MedicalRecordResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(medicalRecordResources);
    }

    @GetMapping("/{medicalRecordId}")
    public ResponseEntity<MedicalRecordResource> getMedicalRecordById(@PathVariable Long medicalRecordId) {
        var getMedicalRecordByIdQuery = new GetMedicalRecordByIdQuery(medicalRecordId);
        var medicalRecord = medicalRecordQueryService.handle(getMedicalRecordByIdQuery);
        if (medicalRecord.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var medicalRecordResource = MedicalRecordResourceFromEntityAssembler.toResourceFromEntity(medicalRecord.get());
        return ResponseEntity.ok(medicalRecordResource);
    }

    @DeleteMapping("/{medicalRecordId}")
    public ResponseEntity<?> deleteMedicalRecord(@PathVariable Long medicalRecordId) {
        var deleteMedicalRecordCommand = new DeleteMedicalRecordCommand(medicalRecordId);
        medicalRecordCommandService.handle(deleteMedicalRecordCommand);
        return ResponseEntity.ok("Medical record with given id successfully deleted");
    }
}