package com.agenda.agendaLagoinha.requests;


import com.agenda.agendaLagoinha.domain.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotNull
    private Long age;

    @NotNull
    private Sexo sexo;

}
