package com.agenda.agendaLagoinha.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private String password;
}
