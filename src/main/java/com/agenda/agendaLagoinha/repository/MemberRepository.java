package com.agenda.agendaLagoinha.repository;

import com.agenda.agendaLagoinha.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface MemberRepository extends JpaRepository<Member, Long> {

}
