package com.agenda.agendaLagoinha.exception.memberException;

public class MemberExistException extends RuntimeException{
    public MemberExistException() {super("User already exists");}
}
