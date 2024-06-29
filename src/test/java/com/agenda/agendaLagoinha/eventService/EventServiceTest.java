package com.agenda.agendaLagoinha.eventService;

import com.agenda.agendaLagoinha.event.Event;
import com.agenda.agendaLagoinha.event.EventRepository;
import com.agenda.agendaLagoinha.event.EventService;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberRepository;
import com.agenda.agendaLagoinha.member.MemberService;
import com.agenda.agendaLagoinha.member.Sexo;
import com.agenda.agendaLagoinha.requests.CreateEventRequest;
import com.agenda.agendaLagoinha.requests.CreateMemberRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;


@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void createEvent(){

        Event event = new Event(
                1L,
                "evento",
                null,
                null
        );



        eventService.insert(event);
        List<Event> events = new ArrayList<>();
        events.add(event);

        assertEquals(1, events.size());
        assertEquals(event, events.get(0));
    }
}
