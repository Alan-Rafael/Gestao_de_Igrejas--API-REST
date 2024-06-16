package com.agenda.agendaLagoinha.Admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


@Builder
@Table
@Entity(name = "admin_entity")
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email(message = "Digite um email Valido")
    private String Email;
    @CPF(message = "Digite um CPF Válido")
    private String cpf;
    private String phone;
    private String password;
}
