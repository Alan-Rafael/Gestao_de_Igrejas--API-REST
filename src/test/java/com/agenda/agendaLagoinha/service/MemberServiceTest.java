package com.agenda.agendaLagoinha.service;


import com.agenda.agendaLagoinha.member.exception.MemberNotFoundException;
import com.agenda.agendaLagoinha.member.domain.Member;
import com.agenda.agendaLagoinha.domain.Sexo;
import com.agenda.agendaLagoinha.member.service.MemberService;
import com.agenda.agendaLagoinha.member.repository.MemberRepository;
import com.agenda.agendaLagoinha.requests.CreateMemberRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Captor
    private ArgumentCaptor<String> idArgumentCaptor;

    @Captor
    private ArgumentCaptor<Member> memberArgumentCaptor;

    @Nested
    class createMember {
        @Test
        @DisplayName("criar novo membro com sucesso")
        void showCreatedMember() {

            var member = new Member(
                    null, "12669321437",
                    "Rafael", 20L,
                    Sexo.MAN, null,
                    null, null

            );

            var input = new CreateMemberRequest(
                    "Rafael",  "12669321437",
                    20L, Sexo.MAN
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
        void ExecaoNaCriacao() {

            var member = new Member(
                    null, "12669321437",
                    "Rafael", 20L,
                    Sexo.MAN, null,
                    null, null
            );

            doThrow(new RuntimeException()).when(memberRepository).save(any());
            assertThrows(RuntimeException.class, () -> memberRepository.save(member));

        }
    }

    @Nested
    class getMembers {

        @Test
        @DisplayName("Encontrando membro pelo cpf")
        void getMemberforId() {
            var member = new Member(
                    null, "12669321437",
                    "Rafael", 20L,
                    Sexo.MAN, null,
                    null, null
            );

            doReturn(member).when(memberRepository).findByCpf(idArgumentCaptor.capture());
            var output = memberService.getOneMember(member.getCpf());
            assertEquals(member.getCpf(), idArgumentCaptor.getValue());
            assertEquals(member, output);

        }

        @Test
        @DisplayName("Lançar exceção quando nao encontrar membro by Cpf")
        void getGetMemberforCpf_NotFound(){
            var cpf = "12669321437";

            doThrow(new MemberNotFoundException()).when(memberRepository).findByCpf(cpf);

            assertThrows(MemberNotFoundException.class, () -> {
                memberService.getOneMember(cpf);
            });
        }

    }

    @Nested
    class returnListOfMembers{
        @Test
        void shoudReturnListOfMembers (){

            var pessoa = new Member(
                    null, "rafael",
                    "11223434", 12L,
                    Sexo.MAN, null,
                    null, null
            );

            var memberList = List.of(pessoa);
            doReturn(memberList).when(memberRepository).findAll();

            var output = memberService.ShowAllMembers();
            assertNotNull(output);
            assertEquals(memberList.size(), output.size());



        }
    }
}