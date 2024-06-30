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
        Member member2 = new Member();
        member2.setCpf("11223344556");

        Set<Member>members = new HashSet<>();
        members.add(member1);
        members.add(member2);
        memberRepository.save(member1);


        var ministryRequest = new CreateMinistryRequest(
                "minister test",
                member1.getCpf(),
                List.of("09876543211", "11223344556")
        );
        when(memberRepository.findByCpfIn(anyList())).thenReturn(members);
        when(request.getAttribute("admin_id")).thenReturn(adminId.toString());
        when(ministryRepository.save(any(Ministry.class))).thenAnswer(i -> i.getArguments()[0]);

        Ministry result = new Ministry(
            null,
            "ministrie test",
                member1,
                members
        );
        ministryService.insert(request, ministryRequest);

        assertNotNull(result);
        assertEquals(ministryRequest.getName(), result.getName());
        assertEquals(members, result.getMinistryMembers());
        assertEquals(adminId, result.getAdminId());

        verify(memberRepository).findByCpfIn(ministryRequest.getMembers());
        verify(request).getAttribute("admin_id");
        verify(ministryRepository).save(result);
    }




}
