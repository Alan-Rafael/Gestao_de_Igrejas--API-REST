package com.agenda.agendaLagoinha.requests.MemberRequests;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberAuthDto {
    private String email;
    private String password;
}
