package com.agenda.agendaLagoinha.Exception;

public class MinistryNotFoundException  extends RuntimeException{
    public MinistryNotFoundException(){
        super("ministry does not exist");
    }
}
