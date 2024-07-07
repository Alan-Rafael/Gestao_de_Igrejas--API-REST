package com.agenda.agendaLagoinha.event.services;


import com.agenda.agendaLagoinha.event.Event;
import com.agenda.agendaLagoinha.event.EventRepository;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberRepository;
import com.agenda.agendaLagoinha.requests.CreateEventRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AddEventService {
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    public AddEventService(EventRepository eventRepository, MemberRepository memberRepository) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
    }

    public Event insert(CreateEventRequest createEventRequest, HttpServletRequest request){

        Set<Member> listaDeMembros = new HashSet<>(memberRepository.findByCpfIn(createEventRequest.getEventMembers()));
        var companyId = request.getAttribute("admin_id");

        var event = Event.builder()
                .eventName(createEventRequest.getName())
                .adminId(UUID.fromString(companyId.toString()))
                .eventMembers(listaDeMembros)
                .build();

        return this.eventRepository.save(event);
    }
}
