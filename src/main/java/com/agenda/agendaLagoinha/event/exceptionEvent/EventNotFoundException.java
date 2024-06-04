package com.agenda.agendaLagoinha.event.exceptionEvent;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException() {super("Event does not exist");}
}
