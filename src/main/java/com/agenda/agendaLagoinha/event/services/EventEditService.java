package com.agenda.agendaLagoinha.event.services;

import com.agenda.agendaLagoinha.event.exceptionEvent.EventNotFoundException;
import com.agenda.agendaLagoinha.event.models.Event;
import com.agenda.agendaLagoinha.event.repositorys.EventRepository;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberRepository;
import com.agenda.agendaLagoinha.requests.UpdateEventRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class EventEditService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    public EventEditService(EventRepository eventRepository, MemberRepository memberRepository) {
        this.eventRepository = eventRepository;
        this.memberRepository = memberRepository;
    }


    public Event UpdateEvent(Long id, UpdateEventRequest eventToUpadate, HttpServletRequest request){
        Event event = this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        Set<Member> members = new HashSet<>(memberRepository.findByCpfIn(eventToUpadate.getMembers()));
        var companyId = request.getAttribute("admin_id");

         event.setEventName(eventToUpadate.getName());
         event.setEventMembers(members);
         event.setAdminId(UUID.fromString(companyId.toString()));

        return this.eventRepository.save(event);
    }
}
