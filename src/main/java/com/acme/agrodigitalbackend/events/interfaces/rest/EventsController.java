package com.acme.agrodigitalbackend.events.interfaces.rest;

import com.acme.agrodigitalbackend.events.domain.model.queries.GetAllEventsQuery;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetEventByIdQuery;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetEventsByCreatorIdQuery;
import com.acme.agrodigitalbackend.events.domain.services.EventCommandService;
import com.acme.agrodigitalbackend.events.domain.services.EventQueryService;
import com.acme.agrodigitalbackend.events.interfaces.rest.resources.CreateEventResource;
import com.acme.agrodigitalbackend.events.interfaces.rest.resources.EventResource;
import com.acme.agrodigitalbackend.events.interfaces.rest.transform.CreateEventCommandFromResourceAssembler;
import com.acme.agrodigitalbackend.events.interfaces.rest.transform.EventResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/events", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Events", description = "Event Management Endpoints")
public class EventsController {

    private final EventCommandService eventCommandService;
    private final EventQueryService eventQueryService;
    private final UserQueryService userQueryService;

    public EventsController(EventCommandService eventCommandService, 
                           EventQueryService eventQueryService,
                           UserQueryService userQueryService) {
        this.eventCommandService = eventCommandService;
        this.eventQueryService = eventQueryService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    public ResponseEntity<EventResource> createEvent(@RequestBody CreateEventResource createEventResource) {
        // Verificar que el usuario existe y tiene rol RANCHER
        var userQuery = new GetUserByIdQuery(createEventResource.creatorId());
        var userOptional = userQueryService.handle(userQuery);
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        var user = userOptional.get();
        if (!"RANCHER".equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        var createEventCommand = CreateEventCommandFromResourceAssembler.toCommandFromResource(createEventResource);
        var event = eventCommandService.handle(createEventCommand);
        
        if (event.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(event.get());
        return new ResponseEntity<>(eventResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventResource>> getAllEvents() {
        var getAllEventsQuery = new GetAllEventsQuery();
        var events = eventQueryService.handle(getAllEventsQuery);
        var eventResources = events.stream()
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventResources);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResource> getEventById(@PathVariable Long eventId) {
        var getEventByIdQuery = new GetEventByIdQuery(eventId);
        var event = eventQueryService.handle(getEventByIdQuery);
        
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(event.get());
        return ResponseEntity.ok(eventResource);
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<EventResource>> getEventsByCreatorId(@PathVariable Long creatorId) {
        var getEventsByCreatorIdQuery = new GetEventsByCreatorIdQuery(creatorId);
        var events = eventQueryService.handle(getEventsByCreatorIdQuery);
        var eventResources = events.stream()
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventResources);
    }
}