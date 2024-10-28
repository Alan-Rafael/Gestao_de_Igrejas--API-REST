package com.agenda.agendaLagoinha.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin>findByEmail(String email);

}
