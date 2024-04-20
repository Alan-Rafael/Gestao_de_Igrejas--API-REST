package com.agenda.agendaLagoinha.repository;

import com.agenda.agendaLagoinha.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByCpf(String cpf);

    void deleteByCpf(String cpf);

   Set<Member> findByCpfIn(List<String> AllMembers);
}
