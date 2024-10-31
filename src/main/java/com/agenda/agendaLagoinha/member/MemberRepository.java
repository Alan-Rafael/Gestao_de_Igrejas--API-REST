package com.agenda.agendaLagoinha.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
   
    Member findByCpf(String cpf);

    Member findByEmail(String email);

    void deleteByCpf(String cpf);

   // Optional<Member> findByEmail(String email);

   Set<Member> findByCpfIn(List<String> AllMembers);
}
