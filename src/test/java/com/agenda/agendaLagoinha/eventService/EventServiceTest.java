package com.agenda.agendaLagoinha.eventService;

import com.agenda.agendaLagoinha.event.models.Event;
import com.agenda.agendaLagoinha.event.repositorys.EventRepository;
import com.agenda.agendaLagoinha.event.services.AddEventService;
import com.agenda.agendaLagoinha.event.services.EventService;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberRepository;

import com.agenda.agendaLagoinha.event.requests.CreateEventRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private EventService eventService;

    @InjectMocks
    private AddEventService addEventService;


    @Nested
    public class testToCreateEventsCases{
        @Test
        public void createEvent() {
            Member member1 = new Member();
            member1.setCpf("09876543211");
            Member member2 = new Member();
            member2.setCpf("11223344556");

            var createEventRequest = new CreateEventRequest(
                    "Test event",
                    List.of("09876543211", "11223344556")
            );


            var adminId = UUID.randomUUID();

            when(request.getAttribute("admin_id")).thenReturn(adminId.toString());
            when(eventRepository.save(any(Event.class))).thenAnswer(i -> i.getArguments()[0]);

            Event result = addEventService.insert(createEventRequest, request);

            assertNotNull(result);
            assertEquals(createEventRequest.getName(), result.getEventName());
            assertEquals(adminId, result.getAdminId());

            verify(request).getAttribute("admin_id");
            verify(eventRepository).save(result);
        }

    }

}
