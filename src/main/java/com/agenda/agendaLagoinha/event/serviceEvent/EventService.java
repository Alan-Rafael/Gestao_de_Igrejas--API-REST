package com.agenda.agendaLagoinha.event.serviceEvent;


import com.agenda.agendaLagoinha.event.exceptionEvent.EventNotFoundException;
import com.agenda.agendaLagoinha.event.domainEvent.Event;
import com.agenda.agendaLagoinha.member.domain.Member;
import com.agenda.agendaLagoinha.event.repositoryEvent.EventRepository;
import com.agenda.agendaLagoinha.member.repository.MemberRepository;
import com.agenda.agendaLagoinha.requests.UpdateEventRequest;
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

    public Event insert(Event event){
        return this.eventRepository.save(event);
    }

    public Set<Event> ShowAll(){
        return new HashSet<>(this.eventRepository.findAll());
    }

    public ResponseEntity<Object> deleteEvent(Long id){
        Event event = this.eventRepository.findById(id).
                orElseThrow(EventNotFoundException::new);

        this.eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public Event showEvent(Long id){
        return this.eventRepository.findById(id).
                orElseThrow(EventNotFoundException::new);

    }
    public Event UpdateEvent(Long id, UpdateEventRequest eventToUpadate){
        Event event = this.eventRepository.findById(id).
                orElseThrow(EventNotFoundException::new);
        Set<Member>members = new HashSet<>(memberRepository.findByCpfIn(eventToUpadate.getMembers()));
        event.setEventName(eventToUpadate.getName());
        event.setEventMembers(members);
        return this.eventRepository.save(event);
    }
}
