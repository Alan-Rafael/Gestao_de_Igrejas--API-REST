package com.agenda.agendaLagoinha.event;


import com.agenda.agendaLagoinha.View.ViewEvent;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberRepository;
import com.agenda.agendaLagoinha.requests.CreateEventRequest;
import com.agenda.agendaLagoinha.requests.UpdateEventRequest;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/churchManagement/event")
public class EventController {

    private final EventService eventService;

    private final MemberRepository memberRepository;

    public EventController(EventService eventService, MemberRepository memberRepository, EventRepository eventRepository) {
        this.eventService = eventService;
        this.memberRepository = memberRepository;
    }


    @PostMapping
    @JsonView(ViewEvent.Base.class)
    public ResponseEntity<Event> addEvent(@RequestBody CreateEventRequest createEventRequest, HttpServletRequest request){
        Set<Member> listaDeMembros = new HashSet<>(memberRepository.findByCpfIn(createEventRequest.getEventMembers()));

        var companyId = request.getAttribute("admin_id");

        var event = Event.builder()
                .eventName(createEventRequest.getName())
                .adminId(UUID.fromString(companyId.toString()))

                .eventMembers(listaDeMembros)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(this.eventService.insert(event));
    }


    @GetMapping("/getAll")
    @JsonView({ViewEvent.Base.class})
    public ResponseEntity<Set<Event>> ShowAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.eventService.ShowAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteEvent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.eventService.deleteEvent(id));

    }

    @GetMapping("/{id}")
    @JsonView(ViewEvent.Admin.class)
    public ResponseEntity<Event> showOneEvent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.eventService.showEvent(id));
    }

    @PutMapping("/{id}")
    @JsonView(ViewEvent.Base.class)
    public  ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody UpdateEventRequest updateEventRequest){
        return ResponseEntity.ok(this.eventService.UpdateEvent(id, updateEventRequest));

    }


}


