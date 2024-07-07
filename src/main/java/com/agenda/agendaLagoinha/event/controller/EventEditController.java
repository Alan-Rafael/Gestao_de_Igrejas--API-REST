package com.agenda.agendaLagoinha.event.controller;

import com.agenda.agendaLagoinha.View.ViewEvent;
import com.agenda.agendaLagoinha.event.models.Event;
import com.agenda.agendaLagoinha.event.services.EventEditService;
import com.agenda.agendaLagoinha.requests.UpdateEventRequest;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/EventEdit")
public class EventEditController {

    private  final EventEditService eventEditService;

    public EventEditController(EventEditService eventEditService) {
        this.eventEditService = eventEditService;
    }

    @PutMapping("/{id}")
    @JsonView(ViewEvent.Base.class)
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody UpdateEventRequest updateEventRequest, HttpServletRequest request){
        return ResponseEntity.ok(this.eventEditService.UpdateEvent(id, updateEventRequest, request));

    }
}
