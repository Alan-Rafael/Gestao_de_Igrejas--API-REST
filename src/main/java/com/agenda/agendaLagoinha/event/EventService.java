package com.agenda.agendaLagoinha.event;


import com.agenda.agendaLagoinha.exception.exceptionEvent.EventNotFoundException;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberRepository;
import com.agenda.agendaLagoinha.requests.eventRequests.CreateEventRequest;
import com.agenda.agendaLagoinha.requests.eventRequests.UpdateEventRequest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    public EventService(EventRepository eventRepository, MemberRepository memberRepository) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
    }

    public Set<Event> ShowAll( ){
        return new HashSet<>(this.eventRepository.findAll());
    }


    public Event showEvent(Long id){
        return this.eventRepository.findById(id).
                orElseThrow(EventNotFoundException::new);

    }

    public Event UpdateEvent(Long id, UpdateEventRequest eventToUpadate, HttpServletRequest request){
        Event event = this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        Set<Member> members = new HashSet<>(memberRepository.findByCpfIn(eventToUpadate.getMembers()));

        event.setEventName(eventToUpadate.getName());
        event.setEventMembers(members);

        return this.eventRepository.save(event);
    }

    public ResponseEntity<Object> deleteEvent(Long id, HttpServletRequest request){
        this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        this.eventRepository.deleteById(id);
        return ResponseEntity.ok().body("Evento Deletado com sucesso");
    }



    public Event insert(CreateEventRequest createEventRequest, HttpServletRequest request){

        Set<Member> listaDeMembros = new HashSet<>(memberRepository.findByCpfIn(createEventRequest.getEventMembers()));

        var event = Event.builder()
                .eventName(createEventRequest.getName())
                .eventMembers(listaDeMembros)
                .build();

        return this.eventRepository.save(event);
    }
}
