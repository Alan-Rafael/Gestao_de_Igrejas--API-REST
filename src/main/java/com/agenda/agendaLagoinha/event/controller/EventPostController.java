package com.agenda.agendaLagoinha.event.controller;

import com.agenda.agendaLagoinha.View.ViewEvent;
import com.agenda.agendaLagoinha.event.models.Event;
import com.agenda.agendaLagoinha.event.services.AddEventService;
import com.agenda.agendaLagoinha.event.requests.CreateEventRequest;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addEventController")

public class EventPostController {

    private final AddEventService addEventService;


    public EventPostController(AddEventService addEventService) {
        this.addEventService = addEventService;
    }

    @PostMapping
    @JsonView(ViewEvent.Base.class)
    public ResponseEntity<Event> addEvent
            (@RequestBody CreateEventRequest createEventRequest,
             HttpServletRequest request){

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(this.addEventService.insert(createEventRequest, request));
    }

}
