package com.agenda.agendaLagoinha.requests.MemberRequests;


import com.agenda.agendaLagoinha.member.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    private String email;

    @NotNull
    private Long age;

    @NotNull
    private Sexo sexo;

    private String password;

}
