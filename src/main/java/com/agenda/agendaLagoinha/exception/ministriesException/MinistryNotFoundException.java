package com.agenda.agendaLagoinha.exception.ministriesException;

public class MinistryNotFoundException  extends RuntimeException{
    public MinistryNotFoundException(){
        super("ministry does not exist");
    }
}
