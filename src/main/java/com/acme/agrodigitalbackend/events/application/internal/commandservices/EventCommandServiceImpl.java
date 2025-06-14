package com.acme.agrodigitalbackend.events.application.internal.commandservices;

import com.acme.agrodigitalbackend.events.domain.model.aggregates.Event;
import com.acme.agrodigitalbackend.events.domain.model.commands.CreateEventCommand;
import com.acme.agrodigitalbackend.events.domain.model.commands.DeleteEventCommand;
import com.acme.agrodigitalbackend.events.domain.services.EventCommandService;
import com.acme.agrodigitalbackend.events.infrastructure.persistence.jpa.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventCommandServiceImpl implements EventCommandService {

    private final EventRepository eventRepository;

    public EventCommandServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> handle(CreateEventCommand command) {
        var event = new Event(command);
        var createdEvent = eventRepository.save(event);
        return Optional.of(createdEvent);
    }

    @Override
    public void handle(DeleteEventCommand command) {
        eventRepository.deleteById(command.eventId());
    }
}