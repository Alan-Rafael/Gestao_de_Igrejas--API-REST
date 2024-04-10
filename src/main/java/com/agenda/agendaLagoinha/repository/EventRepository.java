package com.agenda.agendaLagoinha.repository;

import com.agenda.agendaLagoinha.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
