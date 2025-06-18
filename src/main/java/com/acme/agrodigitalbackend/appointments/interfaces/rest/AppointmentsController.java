package com.acme.agrodigitalbackend.appointments.interfaces.rest;

import com.acme.agrodigitalbackend.appointments.domain.model.commands.DeleteAppointmentCommand;
import com.acme.agrodigitalbackend.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.acme.agrodigitalbackend.appointments.domain.model.queries.GetAppointmentByIdQuery;
import com.acme.agrodigitalbackend.appointments.domain.model.queries.GetAppointmentsByCreatorQuery;
import com.acme.agrodigitalbackend.appointments.domain.services.AppointmentCommandService;
import com.acme.agrodigitalbackend.appointments.domain.services.AppointmentQueryService;
import com.acme.agrodigitalbackend.appointments.interfaces.rest.resources.AppointmentResource;
import com.acme.agrodigitalbackend.appointments.interfaces.rest.resources.CreateAppointmentResource;
import com.acme.agrodigitalbackend.appointments.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.acme.agrodigitalbackend.appointments.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Appointments", description = "Appointments Management Endpoints")
public class AppointmentsController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;

    public AppointmentsController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@Valid @RequestBody CreateAppointmentResource createAppointmentResource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(createAppointmentResource);
        var appointmentId = appointmentCommandService.handle(createAppointmentCommand);
        if (appointmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAllAppointments() {
        var getAllAppointmentsQuery = new GetAllAppointmentsQuery();
        var appointments = appointmentQueryService.handle(getAllAppointmentsQuery);
        var appointmentResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/creator/{createdBy}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByCreator(@PathVariable Long createdBy) {
        var getAppointmentsByCreatorQuery = new GetAppointmentsByCreatorQuery(createdBy);
        var appointments = appointmentQueryService.handle(getAppointmentsByCreatorQuery);
        var appointmentResources = appointments.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long appointmentId) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }


    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId) {
        var deleteAppointmentCommand = new DeleteAppointmentCommand(appointmentId);
        appointmentCommandService.handle(deleteAppointmentCommand);
        return ResponseEntity.ok("Appointment with given id successfully deleted");
    }
}