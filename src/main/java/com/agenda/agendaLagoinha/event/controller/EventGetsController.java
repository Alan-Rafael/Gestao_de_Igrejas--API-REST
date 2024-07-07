package com.agenda.agendaLagoinha.event.controller;


import com.agenda.agendaLagoinha.View.ViewEvent;
import com.agenda.agendaLagoinha.event.models.Event;
import com.agenda.agendaLagoinha.event.repositorys.EventRepository;
import com.agenda.agendaLagoinha.event.services.EventService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/churchManagement/event")
public class EventGetsController {

    private final EventService eventService;

    public EventGetsController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getAll")
    @JsonView({ViewEvent.Base.class})
    public ResponseEntity<Set<Event>> ShowAll(){

        return ResponseEntity.status(HttpStatus.FOUND).body(this.eventService.ShowAll());
    }



    @GetMapping("/{id}")
    @JsonView(ViewEvent.Admin.class)
    public ResponseEntity<Event> showOneEvent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.eventService.showEvent(id));
    }




}


