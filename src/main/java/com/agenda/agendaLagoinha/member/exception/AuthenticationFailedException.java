package com.agenda.agendaLagoinha.member.exception;

public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException() {
        super("Usuario ou senha errado");
    }
}
