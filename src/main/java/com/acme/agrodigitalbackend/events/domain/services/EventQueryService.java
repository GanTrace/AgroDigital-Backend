package com.acme.agrodigitalbackend.events.domain.services;

import com.acme.agrodigitalbackend.events.domain.model.aggregates.Event;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetAllEventsQuery;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetEventByIdQuery;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetEventsByCreatorIdQuery;

import java.util.List;
import java.util.Optional;

public interface EventQueryService {
    List<Event> handle(GetAllEventsQuery query);
    Optional<Event> handle(GetEventByIdQuery query);
    List<Event> handle(GetEventsByCreatorIdQuery query);
}