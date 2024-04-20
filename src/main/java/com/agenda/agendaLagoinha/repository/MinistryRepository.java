package com.agenda.agendaLagoinha.repository;

import com.agenda.agendaLagoinha.domain.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MinistryRepository extends JpaRepository<Ministry, Long> {
}
