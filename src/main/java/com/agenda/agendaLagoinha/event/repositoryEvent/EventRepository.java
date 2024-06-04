package com.agenda.agendaLagoinha.event.repositoryEvent;

import com.agenda.agendaLagoinha.event.domainEvent.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
