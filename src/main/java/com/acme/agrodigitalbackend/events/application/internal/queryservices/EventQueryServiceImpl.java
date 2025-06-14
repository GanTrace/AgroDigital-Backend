package com.acme.agrodigitalbackend.events.application.internal.queryservices;

import com.acme.agrodigitalbackend.events.domain.model.aggregates.Event;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetAllEventsQuery;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetEventByIdQuery;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetEventsByCreatorIdQuery;
import com.acme.agrodigitalbackend.events.domain.services.EventQueryService;
import com.acme.agrodigitalbackend.events.infrastructure.persistence.jpa.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventQueryServiceImpl implements EventQueryService {

    private final EventRepository eventRepository;

    public EventQueryServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> handle(GetAllEventsQuery query) {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> handle(GetEventByIdQuery query) {
        return eventRepository.findById(query.eventId());
    }

    @Override
    public List<Event> handle(GetEventsByCreatorIdQuery query) {
        return eventRepository.findByCreatorId(query.creatorId());
    }
}