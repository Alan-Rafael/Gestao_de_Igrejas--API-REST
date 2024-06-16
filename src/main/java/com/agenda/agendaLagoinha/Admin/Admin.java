package com.agenda.agendaLagoinha.Admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;


@Builder
@Table
@Entity(name = "admin_entity")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String name;
    @Email(message = "Digite um email Valido")
    private String email;
    @CPF(message = "Digite um CPF Válido")
    private String cpf;
    private String phone;
    private String password;
}
