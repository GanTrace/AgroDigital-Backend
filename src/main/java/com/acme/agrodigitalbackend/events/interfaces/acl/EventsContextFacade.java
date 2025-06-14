package com.acme.agrodigitalbackend.events.interfaces.acl;

import com.acme.agrodigitalbackend.events.domain.model.aggregates.Event;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetEventByIdQuery;
import com.acme.agrodigitalbackend.events.domain.model.queries.GetEventsByCreatorIdQuery;
import com.acme.agrodigitalbackend.events.domain.services.EventQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * EventsContextFacade
 * <p>
 * This class is a facade for the Events context. It provides a simple interface for other contexts to interact with the Events context.
 * </p>
 */
@Service
public class EventsContextFacade {

    private final EventQueryService eventQueryService;

    public EventsContextFacade(EventQueryService eventQueryService) {
        this.eventQueryService = eventQueryService;
    }

    /**
 * Get event by id
 * @param eventId the event id
 * @return the event
 */
    public Optional<Event> fetchEventById(Long eventId) {
        var getEventByIdQuery = new GetEventByIdQuery(eventId);
        return eventQueryService.handle(getEventByIdQuery);
    }

    /**
 * Get events by creator id
 * @param creatorId the creator id
 * @return the list of events
 */
    public List<Event> fetchEventsByCreatorId(Long creatorId) {
        var getEventsByCreatorIdQuery = new GetEventsByCreatorIdQuery(creatorId);
        return eventQueryService.handle(getEventsByCreatorIdQuery);
    }
}