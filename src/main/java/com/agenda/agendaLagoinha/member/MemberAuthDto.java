package com.agenda.agendaLagoinha.member;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberAuthDto {
    private String email;
    private String password;
}
