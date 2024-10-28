package com.agenda.agendaLagoinha.exception.memberException;

public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException() {
        super("Usuario ou senha errado");
    }
}
