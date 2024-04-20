package com.agenda.agendaLagoinha.Exception;

public class MemberExistException extends RuntimeException{
    public MemberExistException() {super("User already exists");}
}
