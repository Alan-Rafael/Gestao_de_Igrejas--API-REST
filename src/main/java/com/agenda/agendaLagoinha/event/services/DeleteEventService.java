package com.agenda.agendaLagoinha.event.services;


import com.agenda.agendaLagoinha.event.exceptionEvent.EventNotFoundException;
import com.agenda.agendaLagoinha.event.models.Event;
import com.agenda.agendaLagoinha.event.repositorys.EventRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteEventService {
    private final EventRepository eventRepository;

    public DeleteEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public ResponseEntity<Object> deleteEvent(Long id, HttpServletRequest request){
           this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        this.eventRepository.deleteById(id);
        return ResponseEntity.ok().body("Evento Deletado com sucesso");
    }
}
