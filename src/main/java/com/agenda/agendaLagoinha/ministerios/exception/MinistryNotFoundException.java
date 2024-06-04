package com.agenda.agendaLagoinha.ministerios.exception;

public class MinistryNotFoundException  extends RuntimeException{
    public MinistryNotFoundException(){
        super("ministry does not exist");
    }
}
