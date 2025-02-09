package com.agenda.agendaLagoinha.memberService;


import com.agenda.agendaLagoinha.requests.MemberRequests.CreateMemberRequest;
import com.agenda.agendaLagoinha.exception.memberException.MemberNotFoundException;
import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.Sexo;
import com.agenda.agendaLagoinha.member.MemberService;
import com.agenda.agendaLagoinha.member.MemberRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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

    @Mock
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<String> idArgumentCaptor;

    @Captor
    private ArgumentCaptor<Member> memberArgumentCaptor;

    @Nested
    class createMember {
        @Test
        @DisplayName("criar novo membro com sucesso")
        void CriarNovoMembroComSucesso() {

            var member = new Member(
                    null,
                    "rafael",
                    "11223434",
                    "alan@email.com",
                    "rafael20pp",
                    12L,
                    Sexo.MAN,
                    null,
                    null,
                    null,
                    false
            );
            var input = new CreateMemberRequest(
                    "Rafael",  
                    "12669321437",
                    "alan@gmail.com",
                    20L, 
                    Sexo.MAN,
                    "oi1425367"
            );

            doReturn(member).when(memberRepository).save(memberArgumentCaptor.capture());
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
        void ExecaoNaCriacaoDeUmNovoMembro() {

            var member = new Member(
                    null,
                    "rafael",
                    "11223434",
                    "alan@email.com",
                    "rafael20pp",
                    12L,
                    Sexo.MAN,
                    null,
                    null,
                    null,
                    false
            );

            doThrow(new RuntimeException()).when(memberRepository).save(any());
            assertThrows(RuntimeException.class, () -> memberRepository.save(member));
        }
    }

    @Nested
    class getMembers {

        @Test
        @DisplayName("Encontrando membro pelo cpf")
        void EncontrarMembroPeloCpf() {
            var member = new Member(
                    null,
                    "rafael",
                    "11223434",
                    "alan@email.com",
                    "rafael20pp",
                    12L,
                    Sexo.MAN,
                    null,
                    null,
                    null,
                    false
            );

            doReturn(member).when(memberRepository).findByCpf(idArgumentCaptor.capture());
            var output = memberService.getOneMember(member.getCpf());
            assertEquals(member.getCpf(), idArgumentCaptor.getValue());
            assertEquals(member, output);
        }

        @Test
        @DisplayName("Lançar exceção quando nao encontrar membro by Cpf")
        void ErroAoEncontrarMembroPeloCpf(){

            var cpf = "12669321437";
            doThrow(new MemberNotFoundException()).
            when(memberRepository).findByCpf(cpf);

            assertThrows(MemberNotFoundException.class, () -> {
                memberService.getOneMember(cpf);
            });
        }
    }

    @Nested
    class returnListOfMembers{
        @Test
        @DisplayName("Retornar uma lista de Membos")
        void shoudReturnListOfMembers (){

            var pessoa = new Member(
                    null,
                    "rafael",
                    "11223434",
                    "alan@email.com",
                    "rafael20pp",
                    12L,
                    Sexo.MAN,
                    null,
                    null,
                    null, 
                    false
            );
            var memberList = List.of(pessoa);
            doReturn(memberList).when(memberRepository).findAll();
            var output = memberService.ShowAllMembers();
            assertNotNull(output);
            assertEquals(memberList.size(), output.size());
        }
    }
}