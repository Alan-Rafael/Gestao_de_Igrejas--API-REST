package com.agenda.agendaLagoinha.member;

import com.agenda.agendaLagoinha.member.exception.MemberNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByCpf(String cpf) throws MemberNotFoundException;

    Member findByEmail(String email);

    void deleteByCpf(String cpf);

   // Optional<Member> findByEmail(String email);

   Set<Member> findByCpfIn(List<String> AllMembers);
}
