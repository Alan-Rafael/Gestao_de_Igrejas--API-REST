package com.agenda.agendaLagoinha.minitries;


import com.agenda.agendaLagoinha.event.Event;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberRepository;
import com.agenda.agendaLagoinha.ministerios.Ministry;
import com.agenda.agendaLagoinha.ministerios.MinistryRepository;
import com.agenda.agendaLagoinha.ministerios.MinistryService;
import com.agenda.agendaLagoinha.requests.CreateMinistryRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MinistryServiceTest {

    @Mock
    private MinistryRepository ministryRepository;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private MinistryService ministryService;


    @Test
    void createMinistry(){
        var adminId = UUID.randomUUID();

        Member member1 = new Member();
        member1.setCpf("09876543211");
        Member lider = new Member();
        lider.setCpf("12669321437");
        Set<Member> members = new HashSet<>();
        members.add(member1);

        var ministryRequest = new CreateMinistryRequest(
                "minister test",
                "12669321437",
                List.of("09876543211")
        );

        var minister = Ministry.builder()
                .name("minister test")
                .leader(lider)
                .adminId(adminId)
                .ministryMembers(null)
                .build();
        lider.addMinisterioQueSouLider(minister);

        when(memberRepository.findByCpf("12669321437")).thenReturn(lider);
        when(memberRepository.findByCpfIn(List.of("09876543211"))).thenReturn(members);
        when(request.getAttribute("admin_id")).thenReturn(adminId.toString());
        when(ministryRepository.save(any(Ministry.class))).thenAnswer(i -> i.getArguments()[0]);

        assertNotNull(minister);
        assertEquals("minister test", minister.getName());
        assertEquals(members, minister.getMinistryMembers());
        assertEquals(adminId, minister.getAdminId());

    }




}
