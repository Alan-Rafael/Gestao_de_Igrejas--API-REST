package com.agenda.agendaLagoinha.repository;

import com.agenda.agendaLagoinha.domain.Ministry;
import jakarta.persistence.PreRemove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinistryRepository extends JpaRepository<Ministry, Long> {

}
