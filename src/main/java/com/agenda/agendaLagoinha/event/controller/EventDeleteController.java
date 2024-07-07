package com.agenda.agendaLagoinha.event.controller;


import com.agenda.agendaLagoinha.event.services.DeleteEventService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deleteEvent")
public class EventDeleteController {
    private final DeleteEventService deleteEventService;

    public EventDeleteController(DeleteEventService deleteEventService) {
        this.deleteEventService = deleteEventService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable Long id, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.deleteEventService.deleteEvent(id, request));

    }
}
