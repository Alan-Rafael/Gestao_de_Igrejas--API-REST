package com.agenda.agendaLagoinha.event.services;


import com.agenda.agendaLagoinha.event.models.Event;
import com.agenda.agendaLagoinha.event.repositorys.EventRepository;
import com.agenda.agendaLagoinha.event.exceptionEvent.EventNotFoundException;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberRepository;
import com.agenda.agendaLagoinha.requests.UpdateEventRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository ) {
        this.eventRepository = eventRepository;
    }

    public Set<Event> ShowAll( ){
        return new HashSet<>(this.eventRepository.findAll());
    }


    public Event showEvent(Long id){
        return this.eventRepository.findById(id).
                orElseThrow(EventNotFoundException::new);

    }

}
