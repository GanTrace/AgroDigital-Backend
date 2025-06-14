package com.acme.agrodigitalbackend.events.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.events.domain.model.aggregates.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCreatorId(Long creatorId);
}