package com.agenda.agendaLagoinha.member.exception;

public class MemberExistException extends RuntimeException{
    public MemberExistException() {super("User already exists");}
}
