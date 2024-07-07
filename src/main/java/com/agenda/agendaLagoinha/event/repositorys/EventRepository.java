package com.agenda.agendaLagoinha.event.repositorys;

import com.agenda.agendaLagoinha.event.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
