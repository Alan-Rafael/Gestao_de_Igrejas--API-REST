package com.agenda.agendaLagoinha.event.controller;


import com.agenda.agendaLagoinha.View.ViewEvent;
import com.agenda.agendaLagoinha.event.Event;
import com.agenda.agendaLagoinha.event.EventRepository;
import com.agenda.agendaLagoinha.event.services.EventService;
import com.agenda.agendaLagoinha.requests.UpdateEventRequest;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/churchManagement/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventService = eventService;
    }

    @GetMapping("/getAll")
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


