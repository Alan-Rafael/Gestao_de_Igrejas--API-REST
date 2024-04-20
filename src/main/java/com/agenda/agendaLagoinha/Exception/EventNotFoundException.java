package com.agenda.agendaLagoinha.Exception;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException() {super("Event does not exist");}
}
