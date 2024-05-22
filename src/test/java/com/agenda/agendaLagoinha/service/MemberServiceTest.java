package com.agenda.agendaLagoinha.service;


import com.agenda.agendaLagoinha.domain.Event;
import com.agenda.agendaLagoinha.domain.Member;
import com.agenda.agendaLagoinha.domain.Sexo;
import com.agenda.agendaLagoinha.repository.MemberRepository;
import com.agenda.agendaLagoinha.requests.CreateMemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Captor
    private ArgumentCaptor<Member> memberArgumentCaptor;

    @Nested
    class createMember{
        @Test
        @DisplayName("criar novo membro com sucesso")
        void showCreatedMember(){

            var member = new Member(
                    null,
                    "12669321437",
                    "Rafael",
                    20L,
                    Sexo.MAN,
                    null,
                    null,
                    null

            );

            doReturn(member).when(memberRepository).save(memberArgumentCaptor.capture());

            var input = new CreateMemberRequest(

                    "Rafael",
                    "12669321437",
                    20L,
                    Sexo.MAN
            );

            var output = memberService.addNewMember(input);

            assertNotNull(output);

            var memberCapturado = memberArgumentCaptor.getValue();

            assertEquals(input.getName(), memberCapturado.getName());
            assertEquals(input.getAge(), memberCapturado.getAge());
            assertEquals(input.getCpf(), memberCapturado.getCpf());
            assertEquals(input.getAge(), memberCapturado.getAge());


        }
        @Test
        @DisplayName("lançar exceção ao adicionar membro incorretamente")
        void ExecaoNaCriacao(){

            var member = new Member(
                    null,
                    "12669321437",
                    "Rafael",
                    20L,
                    Sexo.MAN,
                    null,
                    null,
                    null
            );

            doThrow(new RuntimeException()).when(memberRepository).save(any());

            assertThrows(RuntimeException.class, () -> memberRepository.save(member));

        }
    }



}