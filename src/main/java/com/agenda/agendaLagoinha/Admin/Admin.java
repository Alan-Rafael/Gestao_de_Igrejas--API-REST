package com.agenda.agendaLagoinha.Admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import com.agenda.agendaLagoinha.View.ViewMember;
import com.fasterxml.jackson.annotation.JsonView;

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
    @JsonView({ViewMember.Admin.class})
    private UUID id;

    @NotBlank
    @JsonView({ViewMember.Base.class})
    private String name;

    @Email(message = "Digite um email Valido")
    @JsonView({ViewMember.Base.class})
    private String email;

    @CPF(message = "Digite um CPF Válido")
    @JsonView({ViewMember.Admin.class})
    private String cpf;

    @JsonView({ViewMember.Base.class})
    private String phone;

    private String password;
}
