package com.agenda.agendaLagoinha.event;


import com.agenda.agendaLagoinha.View.ViewEvent;
import com.agenda.agendaLagoinha.requests.eventRequests.CreateEventRequest;
import com.agenda.agendaLagoinha.requests.eventRequests.UpdateEventRequest;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/eventos")

public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/listar")
    @CrossOrigin
    @JsonView({ViewEvent.Base.class})
    public ResponseEntity<Set<Event>> showAll() {
        Set<Event> events = this.eventService.ShowAll();
        if (events.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    @GetMapping("/{id}")
    @JsonView(ViewEvent.Admin.class)
    public ResponseEntity<Event> showOneEvent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.eventService.showEvent(id));
    }

    @PostMapping("adicionar")
    @JsonView(ViewEvent.Base.class)
    public ResponseEntity<Event> addEvent
            (@RequestBody CreateEventRequest createEventRequest,
             HttpServletRequest request){

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(this.eventService.insert(createEventRequest, request));
    }

    @PutMapping("editar/{id}")
    @JsonView(ViewEvent.Base.class)
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody UpdateEventRequest updateEventRequest, HttpServletRequest request){
        return ResponseEntity.ok(this.eventService.UpdateEvent(id, updateEventRequest, request));

    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.eventService.deleteEvent(id, request));
    }
}


