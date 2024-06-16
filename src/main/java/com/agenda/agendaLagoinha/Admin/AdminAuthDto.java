package com.agenda.agendaLagoinha.Admin;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AdminAuthDto {
    private String email;
    private String password;
}
