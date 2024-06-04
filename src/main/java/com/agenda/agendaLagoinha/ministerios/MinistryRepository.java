package com.agenda.agendaLagoinha.ministerios;

import com.agenda.agendaLagoinha.ministerios.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinistryRepository extends JpaRepository<Ministry, Long> {
}
