package com.agenda.agendaLagoinha.exception.exceptionEvent;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException() {super("Event does not exist");}
}
