package com.agenda.agendaLagoinha.controller;


import com.agenda.agendaLagoinha.View.ViewEvent;
import com.agenda.agendaLagoinha.domain.Event;
import com.agenda.agendaLagoinha.repository.EventRepository;
import com.agenda.agendaLagoinha.requests.CreateEventRequest;
import com.agenda.agendaLagoinha.requests.UpdateEventRequest;
import com.agenda.agendaLagoinha.service.EventService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/agendaLagoinha/event")
public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;

    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }


    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody CreateEventRequest createEventRequest){

        final Event event = new Event(
                null,
                createEventRequest.getName(),
                Collections.emptySet()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(this.eventService.insert(event));
    }

    @GetMapping
    @JsonView({ViewEvent.Base.class})
    public ResponseEntity<Set<Event>> ShowAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.eventService.ShowAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteEvent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.eventService.deleteEvent(id));

    }

    @GetMapping("/{id}")
    @JsonView(ViewEvent.Admin.class)
    public ResponseEntity<Event> showOneEvent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.eventService.showEvent(id));
    }

    @PutMapping("/{id}")
    @JsonView(ViewEvent.Base.class)
    public  ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody UpdateEventRequest updateEventRequest){
        return ResponseEntity.ok(this.eventService.UpdateEvent(id, updateEventRequest));

    }
}

