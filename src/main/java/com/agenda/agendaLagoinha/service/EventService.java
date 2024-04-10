package com.agenda.agendaLagoinha.service;


import com.agenda.agendaLagoinha.Exception.EventNotFoundException;
import com.agenda.agendaLagoinha.domain.Event;
import com.agenda.agendaLagoinha.repository.EventRepository;
import com.agenda.agendaLagoinha.requests.UpdateEventRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event insert(Event event){
        return this.eventRepository.save(event);
    }

    public Set<Event> ShowAll(){

        return new HashSet<>(this.eventRepository.findAll());
    }

    public ResponseEntity<Object> deleteEvent(Long id){
        Event event = this.eventRepository.findById(id).
                orElseThrow(EventNotFoundException::new);
        if(event!=null){
            this.eventRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

    public Event showEvent(Long id){
        return this.eventRepository.findById(id).
                orElseThrow(EventNotFoundException::new);

    }
    public Event UpdateEvent(Long id, UpdateEventRequest eventToUpadate){
        Event event = this.eventRepository.findById(id).
                orElseThrow(EventNotFoundException::new);
        event.setEventName(eventToUpadate.getName());
        return this.eventRepository.save(event);
    }
}
