package com.acme.agrodigitalbackend.events.domain.services;

import com.acme.agrodigitalbackend.events.domain.model.aggregates.Event;
import com.acme.agrodigitalbackend.events.domain.model.commands.CreateEventCommand;

import java.util.Optional;

public interface EventCommandService {
    Optional<Event> handle(CreateEventCommand command);
}