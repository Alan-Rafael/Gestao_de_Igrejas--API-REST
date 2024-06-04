package com.agenda.agendaLagoinha.member.repository;

import com.agenda.agendaLagoinha.member.exception.MemberNotFoundException;
import com.agenda.agendaLagoinha.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByCpf(String cpf) throws MemberNotFoundException;

    void deleteByCpf(String cpf);

   Set<Member> findByCpfIn(List<String> AllMembers);
}
